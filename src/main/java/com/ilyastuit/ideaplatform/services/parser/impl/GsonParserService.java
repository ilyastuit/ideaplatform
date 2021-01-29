package com.ilyastuit.ideaplatform.services.parser.impl;

import com.fatboyindustrial.gsonjavatime.Converters;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.ilyastuit.ideaplatform.entities.tickets.TicketList;
import com.ilyastuit.ideaplatform.services.parser.ParserService;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class GsonParserService implements ParserService {

    private JsonReader reader;
    private String file;
    private String dateFormat;

    public GsonParserService(String file, String dateFormat) throws FileNotFoundException {
        this.file = file;
        this.dateFormat = dateFormat;

        reader = new JsonReader(new FileReader(this.file));
    }

    @Override
    public TicketList parse() {
        Gson gson = Converters
                .registerLocalTime(
                        new GsonBuilder()
                                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                                .setDateFormat(this.dateFormat)
                )
                .create();

        return gson.fromJson(reader, TicketList.class);
    }

    public JsonReader getReader() {
        return reader;
    }

    public void setReader(JsonReader reader) {
        this.reader = reader;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }
}
