package com.byr.assistant.core.model;

import java.io.Serializable;

/**
 * User: orange
 * Date: 13-11-19
 * Time: 上午12:26
 */
public class Note implements Serializable {


    private static final long serialVersionUID = 2376398850563598139L;

    private int noteId;

    private String headLine;

    private String content;

    private String editDate;

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getHeadLine() {
        return headLine;
    }

    public void setHeadLine(String headLine) {
        this.headLine = headLine;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEditDate() {
        return editDate;
    }

    public void setEditDate(String editDate) {
        this.editDate = editDate;
    }
}
