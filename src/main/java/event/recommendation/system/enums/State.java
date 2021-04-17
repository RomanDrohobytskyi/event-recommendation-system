package event.recommendation.system.enums;

public class State {
    public enum Date {
        NEW ("New date"),
        DELETED ("Date has been deleted"),
        EDITED ("Date has been edited");

        private String state;

        Date(String state){
            this.state = state;
        }

        public String getState(){
            return state;
        }
    }
}
