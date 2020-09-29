package assignment9;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.lang.Integer.valueOf;

/**
 * The type To do.
 */
// The ToDo object that will populate our ToDo list.
// We are using a Builder pattern to create ToDo objects.
public class ToDo {
    private Integer ID;
    private  String text;
    private Boolean completed;
    private  LocalDate due;
    private  Integer priority;
    private  String category;

    /**
     * Instantiates a new To do.
     * @param builder the builder
     */
    // Constructor for ToDo Object, this takes in a ToDoBuilder object.
    // Our ToDo object class has no setters, so once it is created, it is immutable.
    public ToDo(ToDoBuilder builder) {
        this.ID = builder.ID;
        this.text = builder.text;
        this.completed = builder.completed;
        this.due = builder.due;
        this.priority = builder.priority;
        this.category = builder.category;
    }

    public ToDo(ToDo todo) {
        this.ID = todo.ID;
        this.text = todo.text;
        this.completed = todo.completed;
        this.due = todo.due;
        this.priority = todo.priority;
        this.category = todo.category;
    }
    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getID() {
        return ID;
    }

    /**
     * Gets text.
     *
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * Gets completed.
     *
     * @return the completed
     */
    public Boolean getCompleted() {
        return completed;
    }

    /**
     * Gets due.
     *
     * @return the due
     */
    public LocalDate getDue() {
        return due;
    }

    /**
     * Gets priority.
     *
     * @return the priority
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * Gets category.
     *
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets completed.
     *
     * @param completed the completed
     */
    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    /**
     * The type To do builder.
     */
    // Nested ToDoBuilder class. The reason why we make it a static nested class is
    // because the class is used in the creation of a ToDo object. This ToDoBuilder class
    // allows the creation of ToDo objects based on any combination of input, much better than
    // creating a bunch of constructors within the ToDo object itself.
    // Additionally, it is nested for packaging convenience.
    public static class ToDoBuilder {
        private Integer ID;
        private  String text;
        private Boolean completed;
        private LocalDate due;
        private  Integer priority;
        private  String category;

        /**
         * Sets data at index.
         *
         * @param index the index
         * @param data  the data
         * @return the data at index
         */
        public ToDoBuilder setDataAtIndex(Integer index, String data) {
            switch (index) {
                case 0:
                    return setID(data);
                case 1:
                    return setText(data);
                case 2:
                    return setCompleted(data);
                case 3:
                    return setDue(data);
                case 4:
                    return setPriority(data);
                case 5:
                    return setCategory(data);
                default:
                    return this;
            }
        }

        /**
         * Sets id.
         *
         * @param ID the id
         * @return the id
         */
        public ToDoBuilder setID(String ID) {
            this.ID = valueOf(ID);
            return this;
        }

        /**
         * Sets text.
         *
         * @param text the text
         * @return the text
         */
        public ToDoBuilder setText(String text) {
            this.text = text;
            return this;
        }

        /**
         * Sets completed.
         *
         * @param completed the completed
         * @return the completed
         */
        public ToDoBuilder setCompleted(String completed) {
            this.completed = completed.equals("true");
            return this;
        }

        /**
         * Sets due.
         *
         * @param due the due
         * @return the due
         */
        public ToDoBuilder setDue(String due) {
            if (due.equals("?")) {
                this.due = LocalDate.of(9999, 12, 31);
                return this;
            }
            // parses the date instead of leaving it in a string and comparing
            String[] dueParts = due.split("/");
            //              0    1      2
            // dueParts = ["2", "28", "2020"]
            this.due = LocalDate.of(Integer.parseInt(dueParts[2]), Integer.parseInt(dueParts[0]), Integer.parseInt(dueParts[1]));
            return this;
        }

        /**
         * Sets priority.
         * @param priority the priority
         * @return the priority
         */
        public ToDoBuilder setPriority(String priority) {
            this.priority = priority.equals("?") ? 3 : Integer.parseInt(priority);
            return this;
        }

        /**
         * Sets category.
         *
         * @param category the category
         * @return the category
         */
        public ToDoBuilder setCategory(String category) {
            if (category.equals("?")) {
                this.category = "unknown";
                return this;
            }
            this.category = category;
            return this;
        }

        /**
         * Build to do.
         *
         * @return the to do
         */
        public ToDo build() {
            ToDo todo = new ToDo(this);
            return todo;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof ToDoBuilder)) {
                return false;
            }
            ToDoBuilder that = (ToDoBuilder) o;
            return Objects.equals(ID, that.ID) &&
                Objects.equals(text, that.text) &&
                Objects.equals(completed, that.completed) &&
                Objects.equals(due, that.due) &&
                Objects.equals(priority, that.priority) &&
                Objects.equals(category, that.category);
        }

        @Override
        public int hashCode() {
            return Objects.hash(ID, text, completed, due, priority, category);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ToDo)) {
            return false;
        }
        ToDo toDo = (ToDo) o;
        return Objects.equals(getID(), toDo.getID()) &&
            Objects.equals(getText(), toDo.getText()) &&
            Objects.equals(getCompleted(), toDo.getCompleted()) &&
            Objects.equals(getDue(), toDo.getDue()) &&
            Objects.equals(getPriority(), toDo.getPriority()) &&
            Objects.equals(getCategory(), toDo.getCategory());
    }

    @Override
    public int hashCode() {
        return Objects
            .hash(getID(), getText(), getCompleted(), getDue(), getPriority(), getCategory());
    }

    @Override
    public String toString() {
        return
            encapsulateQuotations(String.valueOf(ID)) + ", " +
                encapsulateQuotations(text) + ", " +
                encapsulateQuotations(String.valueOf(completed)) + ", " +
                encapsulateQuotations(due.getMonthValue() + "/" + due.getDayOfMonth() + "/" + due.getYear()) + ", " +
                encapsulateQuotations(String.valueOf(priority)) + ", " +
                encapsulateQuotations(category);
    }

    private String encapsulateQuotations(String string) {
        return "\"" + string + "\"";
    }

    /**
     * The constant DueComparator.
     */
    // Custom comparator based on date, created as a static inner class
    public static final Comparator<ToDo> DueComparator = new Comparator<ToDo>() {
        @Override
        public int compare(ToDo o1, ToDo o2) {
            return o1.getDue().compareTo(o2.getDue());
        }
    };

    /**
     * The constant PriorityComparator.
     */
    // Custom comparator based on date, created as a static inner class
    public static final Comparator<ToDo> PriorityComparator = new Comparator<ToDo>() {
        @Override
        public int compare(ToDo o1, ToDo o2) {
            return o1.getPriority().compareTo(o2.getPriority());
        }
    };
}