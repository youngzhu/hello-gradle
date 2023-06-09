package com.youngzy.todo.web;

import com.youngzy.todo.repository.InMemoryRepository;
import com.youngzy.todo.repository.Repository;

/**
 * @author youngzy
 * @since 2023-04-06
 */
public class TodoServlet {
    public static final String FIND_ALL_SERVLET_PATH = "/all";
    public static final String INDEX_PAGE = "/jsp/todo-list.jsp";
    private Repository toDoRepository = new InMemoryRepository();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        String view = processRequest(servletPath, request);
        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }

    private String processRequest(String servletPath, HttpServletRequest request) {
        if (servletPath.equals(FIND_ALL_SERVLET_PATH)) {
            List<ToDoItem> toDoItems = toDoRepository.findAll();
            request.setAttribute("toDoItems", toDoItems);
            request.setAttribute("stats", determineStats(toDoItems));
            request.setAttribute("filter", "all");
            return INDEX_PAGE;
        } else if (servletPath.equals("/active")) {
            List<ToDoItem> toDoItems = toDoRepository.findAll();
            request.setAttribute("toDoItems", filterBasedOnStatus(toDoItems, true));
            request.setAttribute("stats", determineStats(toDoItems));
            request.setAttribute("filter", "active");
            return INDEX_PAGE;
        } else if (servletPath.equals("/completed")) {
            List<ToDoItem> toDoItems = toDoRepository.findAll();
            request.setAttribute("toDoItems", filterBasedOnStatus(toDoItems, false));
            request.setAttribute("stats", determineStats(toDoItems));
            request.setAttribute("filter", "completed");
            return INDEX_PAGE;
        }
        if (servletPath.equals("/insert")) {
            ToDoItem toDoItem = new ToDoItem();
            toDoItem.setName(request.getParameter("name"));
            toDoRepository.insert(toDoItem);
            return "/" + request.getParameter("filter");
        } else if (servletPath.equals("/update")) {
            ToDoItem toDoItem = toDoRepository.findById(Long.parseLong(request.getParameter("id")));

            if (toDoItem != null) {
                toDoItem.setName(request.getParameter("name"));
                toDoRepository.update(toDoItem);
            }

            return "/" + request.getParameter("filter");
        } else if (servletPath.equals("/delete")) {
            ToDoItem toDoItem = toDoRepository.findById(Long.parseLong(request.getParameter("id")));

            if (toDoItem != null) {
                toDoRepository.delete(toDoItem);
            }

            return "/" + request.getParameter("filter");
        } else if (servletPath.equals("/toggleStatus")) {
            ToDoItem toDoItem = toDoRepository.findById(Long.parseLong(request.getParameter("id")));

            if (toDoItem != null) {
                boolean completed = "on".equals(request.getParameter("toggle")) ? true : false;
                toDoItem.setCompleted(completed);
                toDoRepository.update(toDoItem);
            }

            return "/" + request.getParameter("filter");
        } else if (servletPath.equals("/clearCompleted")) {
            List<ToDoItem> toDoItems = toDoRepository.findAll();

            for (ToDoItem toDoItem : toDoItems) {
                if (toDoItem.isCompleted()) {
                    toDoRepository.delete(toDoItem);
                }
            }

            return "/" + request.getParameter("filter");
        }

        return FIND_ALL_SERVLET_PATH;
    }

    private List<ToDoItem> filterBasedOnStatus(List<ToDoItem> toDoItems, boolean active) {
        List<ToDoItem> filteredToDoItems = new ArrayList<ToDoItem>();

        for (ToDoItem toDoItem : toDoItems) {
            if (toDoItem.isCompleted() != active) {
                filteredToDoItems.add(toDoItem);
            }
        }

        return filteredToDoItems;
    }

    private ToDoListStats determineStats(List<ToDoItem> toDoItems) {
        ToDoListStats toDoListStats = new ToDoListStats();

        for (ToDoItem toDoItem : toDoItems) {
            if (toDoItem.isCompleted()) {
                toDoListStats.addCompleted();
            } else {
                toDoListStats.addActive();
            }
        }

        return toDoListStats;
    }

    public class ToDoListStats {
        private int active;
        private int completed;

        private void addActive() {
            active++;
        }

        private void addCompleted() {
            completed++;
        }

        public int getActive() {
            return active;
        }

        public int getCompleted() {
            return completed;
        }

        public int getAll() {
            return active + completed;
        }
    }
}
