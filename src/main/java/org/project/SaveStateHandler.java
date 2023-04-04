package org.project;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.*;

/**
 * SaveStateHandler manages reading and writing SaveState. Files saved in JSON format.
 *
 * @author Greg Song
 * @version 2023-04-03
 */
public class SaveStateHandler {
  private static SaveStateHandler instance;
  private final SaveState saveState;
  private final String dirPath = "save/";
  private final String extension = ".json";
  private String username;
  private String pathName = dirPath + username + extension;

  /** Private constructor to enforce Singleton pattern. */
  private SaveStateHandler() {
    this.saveState = SaveState.getInstance();
  }

  /**
   * Returns singleton instance of SaveStateHandler.
   * @return SaveStateHandler instance
   */
  public static SaveStateHandler getInstance() {
    if (instance == null) {
      instance = new SaveStateHandler();
    }
    return instance;
  }

  /**
   * Stores save data as a JSON file in save directory.
   */
  public void save() {
    JSONObject jsonSave = new JSONObject();
    jsonSave.put("playerData", saveState.getPlayerData());
    jsonSave.put("gamePanelData", saveState.getGamePanelData());

    FileWriter fileWriter;
    try {
      File file = new File(pathName);
      fileWriter = new FileWriter(file);
      fileWriter.write(jsonSave.toJSONString());
      fileWriter.close();
    } catch (IOException e) {
      throw new RuntimeException("Could not create or write to file at: " + pathName, e);
    }
  }

  /**
   * Loads save data JSON file from directory and returns saveState.
   * @throws FileNotFoundException When user file is not found
   * @return SaveState object
   */
  public SaveState load() throws FileNotFoundException {
    File saveFile = new File(dirPath + username + extension);
    JSONObject jsonSave;
    try {
      FileReader fileReader = new FileReader(saveFile);
      jsonSave = (JSONObject) JSONValue.parse(fileReader);
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException("Cannot locate file:" + pathName);
    }
    SaveState saveState = SaveState.getInstance();
    saveState.setSaveState(jsonSave);
    return saveState;
  }

  /**
   * Sets username.
   * @param username a String, used as file name of save data
   */
  public void setUsername(String username) {
    this.username = username;
    this.pathName = dirPath + username + extension;
  }
}
