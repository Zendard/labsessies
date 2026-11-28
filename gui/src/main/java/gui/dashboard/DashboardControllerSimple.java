package gui.dashboard;



public class DashboardControllerSimple{
    private DashboardModel dashboardModel;
    private final static DashboardControllerSimple controller = new DashboardControllerSimple();

    public static DashboardControllerSimple getController(){return controller;}
    void setDashboardModel(DashboardModel model){
        this.dashboardModel=model;
    }

    void initialize(){}
}
