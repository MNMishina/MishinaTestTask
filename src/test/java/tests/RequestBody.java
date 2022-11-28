package tests;

public class RequestBody {

    public static String CreateUser()
    {
        return "{\n" +
                "    \"name\": \"neo\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";
    }

    public static String UpdateUser()
    {
        return "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"zion resident\"\n" +
                "}";
    }
}
