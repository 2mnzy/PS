package net.warn.db;

public class WarnBoard {
   int ROWNUM;
   int WB_NUM;
   String WM_ID;
   String WB_TITLE;
   int WB_COUNT;
   String WB_DATE;
   String WB_CONTENT;
   String WB_TAG1;
   String WB_TAG2;

   
   public int getROWNUM() {
      return ROWNUM;
   }
   public void setROWNUM(int ROWNUM) {
      this.ROWNUM = ROWNUM;
   }
   public int getWB_NUM() {
      return WB_NUM;
   }
   public void setWB_NUM(int wB_NUM) {
      WB_NUM = wB_NUM;
   }
   public String getWM_ID() {
      return WM_ID;
   }
   public void setWM_ID(String wM_ID) {
      WM_ID = wM_ID;
   }
   public String getWB_TITLE() {
      return WB_TITLE;
   }
   public void setWB_TITLE(String wB_TITLE) {
      WB_TITLE = wB_TITLE;
   }
   public int getWB_COUNT() {
      return WB_COUNT;
   }
   public void setWB_COUNT(int wB_COUNT) {
      WB_COUNT = wB_COUNT;
   }
   public String getWB_DATE() {
      return WB_DATE;
   }
   public void setWB_DATE(String wB_DATE) {
      WB_DATE = wB_DATE;
   }
   public String getWB_CONTENT() {
      return WB_CONTENT;
   }
   public void setWB_CONTENT(String wB_CONTENT) {
      WB_CONTENT = wB_CONTENT;
   }
   public String getWB_TAG1() {
      return WB_TAG1;
   }
   public void setWB_TAG1(String wB_TAG1) {
      WB_TAG1 = wB_TAG1;
   }
   public String getWB_TAG2() {
      return WB_TAG2;
   }
   public void setWB_TAG2(String wB_TAG2) {
      WB_TAG2 = wB_TAG2;
   }
}