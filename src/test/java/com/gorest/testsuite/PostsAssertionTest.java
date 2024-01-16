package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsIterableContaining.hasItems;

public class PostsAssertionTest extends TestBase {
    static ValidatableResponse response;

    @BeforeClass
    public static void start() {
        response = given()
                .queryParam("page", 1)
                .queryParam("per_page", 25)
                .when()
                .get("/posts")
                .then().statusCode(200);
    }

    //1. Verify the if the total record is 25
    @Test
    public void test001() {
        response.body("size()", equalTo(25));
    }

    //2. Verify the if the title of id = 93941 is equal to ”Amissio demens cena degusto vigor bellicus brevis.”
    @Test
    public void test002() {
        response.body("find{it.id ==93941 }.title", equalTo("Amissio demens cena degusto vigor bellicus brevis."));

    }

    //3. Check the single user_id in the Array list (5914156)
    @Test
    public void test003() {
        response.body("[5].user_id", equalTo(5914156));
    }

    //4. Check the multiple ids in the ArrayList (2693, 2684,2681)
    @Test
    public void test004() {
        response.body("id", hasItems(93821, 93822, 93823));
    }

    //5. Verify the body of userid = 2678 is equal “Carus eaque voluptatem. Calcarspectaculum coniuratio. Abstergo consequatur deleo. Amiculum advenio dolorem.
    //Sollers conservo adiuvo. Concedo campana capitulus. Adfectus tibi truculenter.
    //Canto temptatio adimpleo. Ter degenero animus. Adeo optio crapula. Abduco et
    //antiquus. Chirographum baiulus spoliatio. Suscipit fuga deleo. Comburo aequus
    //cuppedia. Crur cuppedia voluptates. Argentum adduco vindico. Denique undique
    //adflicto. Assentator umquam pel."”

    @Test
    public void test005() {
        response.body("find{it.user_id == 5914056}.body", equalTo("Audacia voluptas demum. Surculus illum adsum. Itaque ab cimentarius. Thymbra curvo quos. Ubi ara charisma. Tener taceo quam. Desino advoco vero. Nulla fugiat cras. Unus adduco accusamus. Cinis soluta aveho. Verecundia tandem bibo. Tergo quaerat arca. Usitas cornu aut. Temperantia quia tardus. Surgo certo voco. Demergo voro vulgaris. Aspernatur capillus adipisci. Adficio ullam consuasor. Aegre aveho aliquam."));
    }


}
