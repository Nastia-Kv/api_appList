public class AppListRequest {
    private CurrentState currentState;
    private String filter;
    private String widgetState;

    public CurrentState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(CurrentState currentState) {
        this.currentState = currentState;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getWidgetState() {
        return widgetState;
    }

    public void setWidgetState(String widgetState) {
        this.widgetState = widgetState;
    }



}
