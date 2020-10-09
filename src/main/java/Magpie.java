/**
 * A program to carry on conversations with a human user.
 * This is the initial version that:  
 * <ul><li>
 *       Uses indexOf to find strings
 * </li><li>
 *          Handles responding to simple words and phrases 
 * </li></ul>
 * This version uses a nested if to handle default responses.
 * @author Laurie White
 * @version April 2012
 */
public class Magpie
{
    /**
     * Get a default greeting   
     * @return a greeting
     */
    public String getGreeting()
    {
        return "Hello, let's talk.";
    }
    
    /**
     * Gives a response to a user statement
     * 
     * @param statement
     *            the user statement
     * @return a response based on the rules given
     */
    public String getResponse(String statement)
    {
        String response = "";
        statement = statement.trim();
        if(findWord(statement, "I want to") >= 0){
            response = transformIWantToStatement(statement);
        }
        else if(findWord(statement, "I want") >= 0){
            response = transformIWantStatement(statement);
        }
        else if(findWord(statement, "I") != -1 && findWord(statement, "I") < findWord(statement, "you")){
            response = transformIYouStatement(statement);
        }
        else if(findWord(statement, "I") != -1 && findWord(statement, "you") < findWord(statement, "me")){
            response = transformYouMeStatement(statement);
        }
        else if (findWord(statement, "no") >= 0)
        {
            response = "Why so negative?";
        }
        else if (findWord(statement, "mother") >= 0
                || findWord(statement, "father") >= 0
                || findWord(statement, "sister") >= 0
                || findWord(statement, "brother") >= 0)
        {
            response = "Tell me more about your family.";
        }
        else if (findWord(statement, "dog") >= 0
                || findWord(statement, "cat") >= 0
                || findWord(statement, "gecko") >= 0
                || findWord(statement, "fish") >= 0)
        {
            response = "Tell me more about your pets";
        }
        else if (findWord(statement, "nathan") >= 0){
            response = "He seems like a good teacher";
        }
        else if (findWord(statement, "hungry") >= 0) {
            response = "I'm hungry too! Tell me about your favorite food.";
        }
        else if (findWord(statement, "tired") >= 0){
            response = "You should probably get more sleep";
        }
        else if (findWord(statement, "hello") >= 0){
            response = "Hello, user!";
        }
        else if(statement.length() == 0){
            response = "Say something, please.";
        }
        else
        {
            response = getRandomResponse();
        }

        return response;

    }
    
    /**
     * Pick a default response to use if nothing else fits.
     * @return a non-committal string
     */
    public String getRandomResponse()
    {
        final int NUMBER_OF_RESPONSES = 6;
        double r = Math.random();
        int whichResponse = (int)(r * NUMBER_OF_RESPONSES);
        String response = "";
        
        if (whichResponse == 0)
        {
            response = "Interesting, tell me more.";
        }
        else if (whichResponse == 1)
        {
            response = "Hmmm.";
        }
        else if (whichResponse == 2)
        {
            response = "Do you really think so?";
        }
        else if (whichResponse == 3)
        {
            response = "You don't say.";
        }
        else if (whichResponse == 4)
        {
            response = "Thats odd.";
        }
        else if (whichResponse == 5)
        {
            response = "I mean, I guess so.";
        }
        

    
        return response;
    }

    // Checks to see if the String word appears as a whole word
    // in the String str (in this case, a "whole word" means that 
    // word is not just a substring of some larger word in str)

    // This method should work regardless of the capitalization 
    // of str or word

    // The method returns the index of the first character in word
    // if it is found, and returns -1 otherwise. 
    public int findWord(String str, String word) {
        str = str.toLowerCase();
        word = word.toLowerCase();

        //checks if the word is in the string generally
        if (str.indexOf(word) == -1){
            return -1;
        }

        int index = str.indexOf(word);
        int wordLength = word.length();

        boolean beforeWord = (index == 0 || str.charAt(index-1)== ' ');
        boolean afterWord = ((index+wordLength) >= str.length() || str.charAt(index + wordLength) == ' ');


        if (beforeWord && afterWord){
            return index;
        }
        return -1;
    }

    
    // We will work on the following methods later!

    /**
     * Take a statement with "I want <something>." and transform it into 
     * "Would you really be happy if you had <something>?"
     * @param statement the user statement, assumed to contain "I want"
     * @return the transformed statement
     */
    public String transformIWantStatement(String statement)
    {
        int length = statement.length();
  
        int index = findWord(statement, "I want");
        String something = "";
        for (int i = index + 7; i < length; i++){
            something += statement.charAt(i);
        }
        return "Would you really be happy if you had " + something + "?";
    }

    /**
     * Take a statement with "I <something> you" and transform it into 
     * "Why do you <something> me?"
     * @param statement the user statement, assumed to contain "I" followed by "you"
     * @return the transformed statement
     */
    public String transformIYouStatement(String statement)
    {
        int end = findWord(statement, "you");
        int start = findWord(statement, "I");
        String middle = "";

        for (int i = start + 2; i < end; i++){
            middle += statement.charAt(i);
        }
        return "Why do you " + middle + "me?";
    }

    /**
     * Take a statement with "I want to <something>." and transform it into 
     * "What would it mean to <something>?"
     * @param statement the user statement, assumed to contain "I want to"
     * @return the transformed statement
     */
    public String transformIWantToStatement(String statement)
    {
        int start = findWord(statement, "I want to") + 9;
        int length = statement.length();
        String something = "";
        for (int i = start; i < length; i++){
            something += statement.charAt(i);
        }
        return "What would it mean to" + something + "?";
    }




    /**
     * Take a statement with "you <something> me" and transform it into 
     * "What makes you think that I <something> you?"
     * @param statement the user statement, assumed to contain "you" followed by "me"
     * @return the transformed statement
     */
    public String transformYouMeStatement(String statement)
    {
        int end = findWord(statement, "me");
        int start = findWord(statement, "you");
        String middle = "";

        for (int i = start + 4; i < end; i++){
            middle += statement.charAt(i);
        }
        return "What makes you think that I " + middle + "you?";
    }
}
