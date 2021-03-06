/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intent.search;

import dao.CollectionDAO;
import dao.QuestionDAO;
import dao.DomainMessageDAO;
import dao.KeywordSearchDAO;
import dao.SearchDAO;
import dao.SearchableDAO;
import dao.ProgressDAO;
import intent.Intent;
import intent.IntentDTO;

import java.util.ArrayList;
import model.Collection;
import model.Student;
import model.Question;
import model.DomainMessage;
import model.KeywordSearch;
import model.Search;
import model.Searchable;
import model.Progress;
import services.MessageManager;
import services.Predicates;

/**
 *
 * @author joao
 */
public class SearchIntent extends Intent {

    /**
     * 0 - telegramId 1 - studentName 2- message
     *
     * @param args
     * @return
     */
    @Override
    public IntentDTO run(String... args) {
        this.setup(args);

        String[] keywords = MessageManager.extractKeywords(message);

        if (keywords.length > 0 && !"".equals(keywords[0])) {
            ArrayList<KeywordSearch> KeywordSearches = new ArrayList<>();
            KeywordSearch[] keywordSearchDistinct;

            for (String keyword : keywords) {
                KeywordSearchDAO keywordSearchDAO = new KeywordSearchDAO();
                KeywordSearches.addAll(keywordSearchDAO.list(keyword, foundStudent));
            }

            if (KeywordSearches.size() > 0) {
                /**
                 * Save the results that were found
                 */
                Progress progress = (new ProgressDAO()).find(Progress.searchFound);
                Search search = new Search(progress.id, foundStudent.id, message);
                SearchDAO searchDAO = new SearchDAO(search);
                searchDAO.add();

                for (KeywordSearch keywordSearch : KeywordSearches) {
                    searchDAO.addHistory(keywordSearch.keywordId, search);
                }

                /**
                 * Filter the List of "keywordSearches" by their "searchableId".
                 * This ensures we'll only have one occurrence of each
                 * "searchable". Here we also limit the List to it's first 5
                 * values, so it can be displayed without hassle.
                 */
                keywordSearchDistinct = KeywordSearches.stream()
                        .filter(Predicates.distinctByKey(p -> p.searchableId))
                        .limit(5).toArray(KeywordSearch[]::new);
                String[] finalMessage = new String[keywordSearchDistinct.length + 1];
                int index = 1;

                finalMessage[0] = "Os resultados encontrados foram:";

                for (KeywordSearch keywordSearch : keywordSearchDistinct) {
                    SearchableDAO searchableDAO = new SearchableDAO();
                    Searchable searchable = searchableDAO.find(keywordSearch.searchableId);

                    if (searchable.collectionId != 0) {
                        CollectionDAO collectionDAO = new CollectionDAO();
                        Collection collection = collectionDAO.find(searchable.collectionId);
                        String theme = "Tema: " + collection.theme;
                        String author = "Autor: " + collection.author;
                        String advisor = "Orientador: " + collection.advisor;
                        finalMessage[index] = "\n" + theme + "\n" + author + "\n" + advisor;
                    } else {
                        QuestionDAO questionDAO = new QuestionDAO();
                        Question question = questionDAO.find(searchable.questionId);
                        String name = "Dúvida: " + question.name;
                        String description = "Resposta: " + question.description;
                        finalMessage[index] = "\n" + name + "\n" + description
                                .replace(". ", ".\n")
                                .replace(": ", ":\n")
                                .replace("? ", "\n")
                                .replace("! ", "\n");
                    }
                    index++;
                }
                return new IntentDTO(String.join("\n", finalMessage), foundStudent.telegramId);
            }
        }

        /**
         * No results were found
         */
        Progress progress = (new ProgressDAO()).find(Progress.searchNotFound);
        Search search = new Search(progress.id, foundStudent.id, message);
        SearchDAO searchDAO = new SearchDAO(search);
        searchDAO.add();

        return searchNotFound(foundStudent, message);
    }

    /**
     * No results were found for the student's search
     *
     * @param aluno
     * @param message
     * @return
     */
    private IntentDTO searchNotFound(Student aluno, String message) {
        DomainMessageDAO domainMessageDAO = new DomainMessageDAO();
        DomainMessage domainMessage = domainMessageDAO.find(Progress.searchNotFound);

        Progress progress = (new ProgressDAO()).find(Progress.searchNotFound);
        Search search = new Search(progress.id, aluno.id, message);
        SearchDAO searchDAO = new SearchDAO(search);
        searchDAO.add();

        String response = domainMessage.body;
        return new IntentDTO(response, aluno.telegramId);
    }

}
