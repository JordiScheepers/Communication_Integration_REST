package model;

public class Climat {

        protected Country country;
        protected Month month;
        protected int maxTemp;
        protected int minTemp;
        protected int meanSunDays;
        protected int meanRainDays;
        protected Integer seaTemp;

        public Country getCountry() {
            return country;
        }

        public void setCountry(Country value) {
            this.country = value;
        }


        public Month getMonth() {
            return month;
        }


        public void setMonth(Month value) {
            this.month = value;
        }


        public int getMaxTemp() {
            return maxTemp;
        }


        public void setMaxTemp(int value) {
            this.maxTemp = value;
        }


        public int getMinTemp() {
            return minTemp;
        }

        public void setMinTemp(int value) {
            this.minTemp = value;
        }

        public int getMeanSunDays() {
            return meanSunDays;
        }

        public void setMeanSunDays(int value) {
            this.meanSunDays = value;
        }

        public int getMeanRainDays() {
            return meanRainDays;
        }

        public void setMeanRainDays(int value) {
            this.meanRainDays = value;
        }

        public Integer getSeaTemp() {
            return seaTemp;
        }

        public void setSeaTemp(Integer value) {
            this.seaTemp = value;
        }

    }