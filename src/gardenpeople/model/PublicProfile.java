package gardenpeople.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by mark-i5 on 29/11/2014.
 */
public class PublicProfile {

    private String username;
    private String tradename;


    private String description;
    private String googleLocation;
    private float latitude;
    private float longitude;
    private int radius;


    private boolean rhs1;
    private boolean rhs2;
    private boolean rhs3;
    private boolean rhsMaster;
    private boolean maintenanceOffered;
    private boolean designOffered;
    private boolean treeSurgeryOffered;
    private boolean waterFeaturesOffered;
    private boolean fencingOffered;
    private boolean pavingOffered;
    private boolean deckingOffered;

    private boolean isRecordedOnDatabase = false;
    private Date updatedAt;
    ArrayList<ProfileImage> images;

    public PublicProfile(String username) {
        this.username = username;
    }


    public PublicProfile(PublicProfile publicProfile){
        this.username = publicProfile.getUsername();
        this.updatedAt = publicProfile.getUpdatedAt();
        this.isRecordedOnDatabase = publicProfile.isRecordedOnDatabase();
    }

    public void setFromParameterName(String parameterName, String parameterValue){
        switch (parameterName) {
            case "tradename":
                this.tradename = parameterValue;
                break;
            case "description":
                this.description = parameterValue;
                break;
            case "rhs1":
                this.rhs1 = intToBool(parameterValue);
                break;
            case "rhs2":
                this.rhs2 = intToBool(parameterValue);
                break;
            case "rhs3":
                this.rhs3 = intToBool(parameterValue);
                break;
            case "rhsMaster":
                this.rhsMaster = intToBool(parameterValue);
                break;
            case "maintenance":
                this.maintenanceOffered = intToBool(parameterValue);
                break;
            case "design":
                this.designOffered = intToBool(parameterValue);
                break;
            case "treesurgery":
                this.treeSurgeryOffered = intToBool(parameterValue);
                break;
            case "waterfeatures":
                this.waterFeaturesOffered = intToBool(parameterValue);
                break;
            case "fencing":
                this.fencingOffered = intToBool(parameterValue);
                break;
            case "paving":
                this.pavingOffered = intToBool(parameterValue);
                break;
            case "decking":
                this.deckingOffered = intToBool(parameterValue);
                break;
            case "radius":
                this.radius =Integer.parseInt(parameterValue);
                break;
            case "Location":
                this.googleLocation = parameterValue;
                break;
            case "Latitude":
                this.latitude = Float.parseFloat(parameterValue);
                break;
            case "Longitude":
                this.longitude = Float.parseFloat(parameterValue);
                break;

            default:
               break;
        }
    }

    public ArrayList<ProfileImage> getImages() {
        return images;
    }

    public void setImages(ArrayList<ProfileImage> images) {
        this.images = images;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getGoogleLocation() {
        return googleLocation;
    }

    public void setGoogleLocation(String googleLocation) {
        this.googleLocation = googleLocation;
    }
    private boolean intToBool(String value){
        if(value != null && value.equals("1") ){
            return true;
        }

        return false;
    }

    public boolean isRecordedOnDatabase() {
        return isRecordedOnDatabase;
    }

    public void setRecordedOnDatabase(boolean isRecordedOnDatabase) {
        this.isRecordedOnDatabase = isRecordedOnDatabase;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTradename() {
        return tradename;
    }

    public void setTradename(String tradename) {
        this.tradename = tradename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public boolean isRhs1() {
        return rhs1;
    }

    public void setRhs1(boolean rhs1) {
        this.rhs1 = rhs1;
    }

    public boolean isRhs2() {
        return rhs2;
    }

    public void setRhs2(boolean rhs2) {
        this.rhs2 = rhs2;
    }

    public boolean isRhs3() {
        return rhs3;
    }

    public void setRhs3(boolean rhs3) {
        this.rhs3 = rhs3;
    }

    public boolean isRhsMaster() {
        return rhsMaster;
    }

    public void setRhsMaster(boolean rhsMaster) {
        this.rhsMaster = rhsMaster;
    }

    public boolean isMaintenanceOffered() {
        return maintenanceOffered;
    }

    public void setMaintenanceOffered(boolean maintenceOffered) {
        this.maintenanceOffered = maintenceOffered;
    }

    public boolean isDesignOffered() {
        return designOffered;
    }

    public void setDesignOffered(boolean designOffered) {
        designOffered = designOffered;
    }

    public boolean isTreeSurgeryOffered() {
        return treeSurgeryOffered;
    }

    public void setTreeSurgeryOffered(boolean treeSurgeryOffered) {
        this.treeSurgeryOffered = treeSurgeryOffered;
    }

    public boolean isWaterFeaturesOffered() {
        return waterFeaturesOffered;
    }

    public void setWaterFeaturesOffered(boolean waterFeaturesOffered) {
        this.waterFeaturesOffered = waterFeaturesOffered;
    }

    public boolean isFencingOffered() {
        return fencingOffered;
    }

    public void setFencingOffered(boolean fencingOffered) {
        this.fencingOffered = fencingOffered;
    }

    public boolean isPavingOffered() {
        return pavingOffered;
    }

    public void setPavingOffered(boolean pavingOffered) {
        this.pavingOffered = pavingOffered;
    }

    public boolean isDeckingOffered() {
        return deckingOffered;
    }

    public void setDeckingOffered(boolean deckingOffered) {
        this.deckingOffered = deckingOffered;
    }

    public String getFormattedUpdatedAt(){
        String val = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        if(updatedAt != null){
            val = dateFormat.format(updatedAt);

        }

        return val;
    }
}
