package org.jobboard.domain.tag;

import java.util.UUID;

public class TestTagFactory {
    public static Tag emmptyTag(){
        Tag tag = new Tag(UUID.randomUUID());
        tag.setName("Java");
        tag.setAlias(Alias.LANGUAGE);
        return tag;
    }
}
