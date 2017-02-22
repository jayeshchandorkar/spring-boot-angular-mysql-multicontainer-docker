package com.springboot.gamereco.service;

//import com.springboot.gamereco.domain.Recommendation;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

public class RecommendationServiceTest {

//    private RecommendationService recommendationService;
//    private Recommendation recommendation;
//    private Recommendation recommendation2;
//
//    @Before
//    public void setup(){
//        recommendationService = new RecommendationService();
//
//        recommendation = new Recommendation();
//        recommendation.setCustomerNumber(1L);
//        recommendation.setName("Recommendation 1");
//
//        recommendation2 = new Recommendation();
//        recommendation2.setCustomerNumber(2L);
//        recommendation2.setName("Recommendation 2");
//    }
//
//    @Test
//    public void testAddTodoForSuccess() throws Exception {
//        int sizeBeforeAdd = recommendationService.size();
//        recommendationService.addTodo(recommendation);
//
//        assertEquals(recommendationService.size(), 1);
//        assertThat("Test to check if recommendation list size increased after add.", recommendationService.size(), Matchers.greaterThan(sizeBeforeAdd));
//    }
//
//    @Test
//    public void testGetTodoList() throws Exception {
//
//        recommendationService.addTodo(recommendation);
//
//        List<Recommendation> recommendationList = recommendationService.getRecommendations();
//
//        assertThat("Test to check if the size of the returned Recommendation list is more than zero.", recommendationList.size(), Matchers.greaterThan(0));
//    }
//
//    @Test
//    public void testGetTodoListMultiple() throws Exception {
//        recommendationService.addTodo(recommendation);
//        recommendationService.addTodo(recommendation2);
//
//        List<Recommendation> recommendationList = recommendationService.getRecommendations();
//
//        assertThat("Test if all todos from Recommendation list returned", recommendationList.size(), Matchers.equalTo(2));
//    }
//
//
//    @Test
//    public void testUpdateTodoForName() throws Exception{
//        Recommendation updatedRecommendation = null;
//
//        recommendationService.addTodo(recommendation);
//        recommendationService.addTodo(recommendation2);
//
//        Recommendation recommendationToUpdate = new Recommendation();
//        recommendationToUpdate.setCustomerNumber(recommendation2.getCustomerNumber());
//        recommendationToUpdate.setName("Recommendation 2 updated");
//        recommendationService.updateTodo(recommendationToUpdate);
//
//        List<Recommendation> recommendationList = recommendationService.getRecommendations();
//        for (Recommendation recommendationObj : recommendationList){
//            if(recommendationToUpdate.getCustomerNumber() == recommendationObj.getCustomerNumber()){
//                updatedRecommendation = recommendationObj;
//                break;
//            }
//        }
//
//        assertEquals("Test for update of Recommendation name", "Recommendation 2 updated", updatedRecommendation.getName());
//        assertNotEquals("Test to check if the name of other recommendation instance is not updated", "Recommendation 2 updated", recommendation.getName());
//
//    }
//
//    @Test
//    public void testUpdateTodoForCompletedStatus() throws Exception{
//        Recommendation updatedRecommendation = null;
//
//        recommendationService.addTodo(recommendation);
//        recommendationService.addTodo(recommendation2);
//
//        Recommendation recommendationToUpdate = new Recommendation();
//        recommendationToUpdate.setCustomerNumber(recommendation2.getCustomerNumber());
//        recommendationToUpdate.setActive(true);
//        recommendationService.updateTodo(recommendationToUpdate);
//
//        List<Recommendation> recommendationList = recommendationService.getRecommendations();
//        for (Recommendation recommendationObj : recommendationList){
//            if(recommendation2.getCustomerNumber() == recommendationObj.getCustomerNumber()){
//                updatedRecommendation = recommendationObj;
//                break;
//            }
//        }
//
//        assertEquals("Test for update of completed flag", true, updatedRecommendation.isActive());
//        assertNotEquals("Test to check if completed flag of other instance is not updated", true, recommendation.isActive());
//
//    }
//
//    @Test
//    public void testUpdateTodoForDeletedStatus() throws Exception{
//        Recommendation updatedRecommendation = null;
//
//        recommendationService.addTodo(recommendation);
//        recommendationService.addTodo(recommendation2);
//
//        Recommendation recommendationToUpdate = new Recommendation();
//        recommendationToUpdate.setCustomerNumber(recommendation2.getCustomerNumber());
//        recommendationToUpdate.setDeleted(true);
//        recommendationService.updateTodo(recommendationToUpdate);
//
//        List<Recommendation> recommendationList = recommendationService.getRecommendations();
//        for (Recommendation recommendationObj : recommendationList){
//            if(recommendation2.getCustomerNumber() == recommendationObj.getCustomerNumber()){
//                updatedRecommendation = recommendationObj;
//                break;
//            }
//        }
//
//        assertEquals("Test for update of Deleted flag", true, updatedRecommendation.isDeleted());
//        assertNotEquals("Test to check if delete flag is not updated for other instance", true, recommendation.isDeleted());
//
//    }
//
//    @Test
//    public void testDeleteTodoToCheckSizeReduced() throws Exception{
//        recommendationService.addTodo(recommendation);
//        recommendationService.addTodo(recommendation2);
//        recommendationService.deleteTodo(2L);
//
//        assertEquals("Test to check if the recommendation list size is reduced after delete.",1, recommendationService.getRecommendations().size());
//    }
//
//    @Test
//    public void testDeleteTodoToCheckInstanceDeleted() throws Exception{
//        boolean existBeforeDelete = false;
//        boolean existAfterDelete = false;
//
//        recommendationService.addTodo(recommendation);
//        recommendationService.addTodo(recommendation2);
//
//        List<Recommendation> recommendationList = recommendationService.getRecommendations();
//        for (Recommendation recommendationObj : recommendationList){
//            if(recommendationObj.getCustomerNumber() == 2){
//                existBeforeDelete = true;
//                break;
//            }
//        }
//
//        recommendationService.deleteTodo(2L);
//
//        recommendationList = recommendationService.getRecommendations();
//        for (Recommendation recommendationObj : recommendationList){
//            if(recommendationObj.getCustomerNumber() == 2){
//                existAfterDelete = true;
//                break;
//            }
//        }
//
//        assertEquals("Test to check if the existing recommendation instance is present before delete.", true , existBeforeDelete);
//        assertEquals("Test to check if the existing recommendation instance is not present delete.", false , existAfterDelete);
//    }

}
