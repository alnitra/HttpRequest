package ru.netology;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Cat {

    private final String id;
    private final String text;
    private final String type;
    private final String user;
    private final Integer upVotes;

    public Cat(
            @JsonProperty("id") String id,
            @JsonProperty("text") String text,
            @JsonProperty("type") String type,
            @JsonProperty("user") String user,
            @JsonProperty("upvotes") Integer upVotes
    ) {
        this.id = id;
        this.text = text;
        this.type = type;
        this.user = user;
        this.upVotes = upVotes;
    }

    public String getId(){
        return id;
    }

    public String getText(){
        return text;
    }

    public String getType(){
        return type;
    }

    public String getUser(){
        return user;
    }

    public Integer getUpVotes(){
        return upVotes;
    }

    public String toString(){
        return "Cat" +
                "\n id=" + id +
                "\n text=" + text +
                "\n type=" + type +
                "\n user=" + user +
                "\n upVotes=" + upVotes;
    }

}
