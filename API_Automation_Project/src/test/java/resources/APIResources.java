package resources;

public enum APIResources {

    addPlaceAPI("/maps/api/place/add/json"),
    getPlaceAPI("/maps/api/place/get/json"),
    deletePlaceAPI("/maps/api/place/delete/json");

    private final String resourcePath;

    
    APIResources(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public String getResource() {
        return resourcePath;
    }
}