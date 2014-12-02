package gardenpeople.validator;

import gardenpeople.model.PublicProfile;

import java.util.ArrayList;

/**
 * Created by mark-i5 on 29/11/2014.
 */
public class ProfileValidator extends Validator {

    private static String model = "profile";
    private static final int MAX_TRADENAME_SIZE = 50;
    private static  final  int MIN_TRADENAME_SIZE = 4;
    private static final int MAX_DESCRIPTION_SIZE = 1000;

    public ProfileValidator(){
        super(model);
    }

    public void checkProfile (PublicProfile publicProfile){

        checkTradeName(publicProfile.getTradename());
        checkDescription(publicProfile.getDescription());
        checkServiceOffered(publicProfile);
        checkLatLong(publicProfile.getLatitude(), publicProfile.getLongitude());

    }

    public  void checkTradeName(String tradeName){
        if(tradeName == null || tradeName.length() < MIN_TRADENAME_SIZE || tradeName.length() > MAX_TRADENAME_SIZE  ){
            errors.add ("Tradename required (must be between "+ MIN_TRADENAME_SIZE+ " and " + MAX_TRADENAME_SIZE + ")");
        }
    }

    public void checkDescription(String description){
        if (description == null || description.length() > MAX_DESCRIPTION_SIZE){
            errors.add ("Description required (maximum " + MAX_DESCRIPTION_SIZE + " characters)");
        }
    }

    public void checkLatLong(Float latitude , Float longitude){
        if(latitude == null || longitude == null){
            errors.add("Your geolocation coordinates are not found, please check your location in catchment field");
        }
    }

    public void checkServiceOffered (PublicProfile publicProfile){

        boolean anyOffered = publicProfile.isMaintenanceOffered();
        anyOffered = anyOffered || publicProfile.isDeckingOffered();
        anyOffered = anyOffered ||  publicProfile.isDesignOffered();
        anyOffered = anyOffered ||  publicProfile.isFencingOffered();
        anyOffered = anyOffered ||  publicProfile.isPavingOffered();
        anyOffered = anyOffered ||  publicProfile.isTreeSurgeryOffered();
        anyOffered = anyOffered ||  publicProfile.isWaterFeaturesOffered();
        if(!anyOffered){
            errors.add("you must select some services for your profile to be returned in search results");
        }

    }


}
