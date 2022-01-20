package _02_cat_facts_API;

public class CatFactsRunner {

    public static void main(String[] args) {
        CatFactsApi catFactsApi = new CatFactsApi();
       // catFactsApi.testRequest();
        String catFact = catFactsApi.findCatFact();
        System.out.println(catFact);
    }

}
