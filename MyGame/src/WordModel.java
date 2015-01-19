/**
 * Created by npolevoy on 19.01.2015.
 */
public class WordModel {

    private String englishWord;

    private String russianWord;

    private byte id;

    public WordModel(String englishWord, String russianWord) {

        this.englishWord = englishWord;

        this.russianWord = russianWord;

        this.id = 0;

    }

////////////

    public String getEnglishWord() {

        return englishWord;

    }

    public void setEnglishWord(String englishWord) {

        this.englishWord = englishWord;

    }

/////////////

    public void setid(byte id) {

        this.id = id;

    }

    public byte getid() {

        return id;

    }

//////////////

    public String getrussianWord() {

        return russianWord;

    }

    public void setRussianWord(String russianWord) {

        this.russianWord = russianWord;

    }

///////

}