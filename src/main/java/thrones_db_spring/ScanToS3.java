package thrones_db_spring;

import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oliverl1
 */
public class ScanToS3 {


    private static final int numCharacters=42;
    private static final int numOrganizations=15;
    private static final int numLocations=32;
    private static final int numEvents=10;
    private static final int numEpisodes=40;

    private static final String domain="http://localhost";



    public static void main(String[] args) {

        RestTemplate restTemplate = new RestTemplate();


        String mainPage=restTemplate.getForObject(domain,String.class);

        List<String> topLevelPages=new ArrayList<String>();
        topLevelPages.add(restTemplate.getForObject(domain+"/characters",String.class));
        topLevelPages.add(restTemplate.getForObject(domain+"/organizations",String.class));
        topLevelPages.add(restTemplate.getForObject(domain+"/locations",String.class));
        topLevelPages.add(restTemplate.getForObject(domain+"/events",String.class));
        topLevelPages.add(restTemplate.getForObject(domain+"/episodes",String.class));
        topLevelPages.add(restTemplate.getForObject(domain+"/about",String.class));



        List<String> characterPages=new ArrayList<String>();
        for(int n=1;n<=numCharacters;n++){
            characterPages.add(restTemplate.getForObject(domain+"/characters/"+n,String.class));
        }


        List<String> organizationPages=new ArrayList<String>();
        for(int n=1;n<=numOrganizations;n++){
            organizationPages.add(restTemplate.getForObject(domain+"/organizations/"+n,String.class));
        }



        List<String> locationPages=new ArrayList<String>();
        for(int n=1;n<=numLocations;n++){
            locationPages.add(restTemplate.getForObject(domain+"/locations/"+n,String.class));
        }


        List<String> eventPages=new ArrayList<String>();
        for(int n=1;n<=numEvents;n++){
            eventPages.add(restTemplate.getForObject(domain+"/events/"+n,String.class));
        }


        List<String> episodesPages=new ArrayList<String>();
        for(int n=1;n<=numEpisodes;n++){
            episodesPages.add(restTemplate.getForObject(domain+"/episodes/"+n,String.class));
        }






    }
}
