//UTCN 2019
//23/03/2019
package model;

public class DataValidator {

    private int minArrival;
    private int maxArrival;
    private int minService;
    private int maxService;
    private int simulationInterval;

    public DataValidator(String minArrival, String maxArrival, String minService, String maxService, String simulationInterval) throws Exception {
        if (minArrival.matches("(\\A[\\d]+\\z)") && maxArrival.matches("(\\A[\\d]+\\z)") && minService.matches("(\\A[\\d]+\\z)")
            && maxService.matches("(\\A[\\d]+\\z)") && simulationInterval.matches("(\\A[\\d]+\\z)")){
            this.minArrival = Integer.parseInt(minArrival);
            this.maxArrival = Integer.parseInt(maxArrival);
            this.minService = Integer.parseInt(minService);
            this.maxService = Integer.parseInt(maxService);
            this.simulationInterval = Integer.parseInt(simulationInterval);
        }
        else {
            throw new Exception("Wrong input data!");
        }
    }

    public int getMinArrival() {
        return minArrival;
    }

    public int getMaxArrival() {
        return maxArrival;
    }

    public int getMinService() {
        return minService;
    }

    public int getMaxService() {
        return maxService;
    }

    public int getSimulationInterval() {
        return simulationInterval;
    }
}
