public class Parser {
    public static Command parse(String input) {
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        String arguments = parts.length > 1 ? parts[1] : "";
        return new Command(command, arguments);
    }
}
