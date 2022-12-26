package workshop;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PlaintextToHtmlConverter {
    String source;
    int currentLocation;
    List<String> result;
    List<String> convertedLine;
    String characterToConvert;

    public String toHtml() throws Exception {
        return basicHtmlEncode(read());
    }

    protected String read() throws IOException {
        return new String(Files.readAllBytes(Paths.get("sample.txt")));
    }

    private String basicHtmlEncode(String source) {
        this.source = source;
        currentLocation = 0;
        result = new ArrayList<>();
        convertedLine = new ArrayList<>();
        characterToConvert = stashNextCharacterAndAdvanceThePointer();

        while (isCurrentLocationOutOfBounds() == false) {
            switch (characterToConvert) {
                case "<":
                    convertedLine.add("&lt;");
                    break;
                case ">":
                    convertedLine.add("&gt;");
                    break;
                case "&":
                    convertedLine.add("&amp;");
                    break;
                case "\n":
                    addANewLine();
                    break;
                default:
                    pushACharacterToTheOutput();
            }
            characterToConvert = stashNextCharacterAndAdvanceThePointer();
        }
        addANewLine();
        return getFinalResult();
    }

    private boolean isCurrentLocationOutOfBounds(){
        return currentLocation >= this.source.length();
    }
    private String stashNextCharacterAndAdvanceThePointer() {
        char currentCharacter = source.charAt(currentLocation);
        incrementCurrentLocation();
        return String.valueOf(currentCharacter);
    }

    private void addANewLine() {
        String line = String.join("", convertedLine);
        result.add(line);
        convertedLine.clear();
    }

    private void pushACharacterToTheOutput() {
        convertedLine.add(characterToConvert);
    }
    private void incrementCurrentLocation(){
        currentLocation++;
    }
    private String getFinalResult(){
        return String.join("<br />", result);
    }
}
