package venox.bot.models.user;

import jakarta.persistence.*;

import java.util.Date;

import static venox.bot.models.user.update.sendUpdateToMaster;

class update{
    public static void sendUpdateToMaster(Long id, Object prop, Object val){
        System.out.println("\nUpdate: " + new Date(System.currentTimeMillis()) + " | " + prop + ": " + val);
    }
}

    @Entity
    @Table(name = "user")
    public class VenoxUser {
        @Id
        @Column(name = "id")
        private Long id = -1L;
        @Column(name = "email")
        private String email = "";
        @Column(name = "password")
        private String password = "";
        @Column(name = "money")
        private Long money = 0L;
        @Column(name = "formattedName")
        private String formattedName = "";
        @Column(name = "socialState")
        private String socialState = "";
        @Column(name = "groupId")
        private String groupId = "";
        @Transient
        private Date lastMessage;
        @Transient
        private int warnLevel = 0;
        @Column(name = "creation_date")
        private Long creation_date = 0L;
        @Column(name = "verified_date")
        private Long verified_date = 0L;
        @Column(name = "adminLevel")
        private int adminLevel = 0;
        @Column(name = "isVerified")
        private boolean isVerified = false;
        @Column(name = "verified_name")
        private String verified_name = "";
        @Column(name = "verified_age")
        private int verified_age = 0;
        @Column(name = "verified_city")
        private String verified_city = "";
        @Column(name = "verified_picture")
        private String verified_picture = "";
        @Column(name = "verified_admin_id")
        private Long verified_admin_id = 0L;
        @Column(name = "verified_admin_username")
        private String verified_admin_username = "";
        @Column(name = "level_background")
        private String level_background = "";
        @Column(name = "me_background")
        private String me_background = "";
        @Column(name = "exp")
        private Long exp = 0L;
        @Transient
        private String levelName = "";
        @Column(name = "vip_package")
        private int vip_package = 0;
        @Column(name = "vip_till")
        private Long vip_till = 0L;
        @Column(name = "language")
        private String language = "";
        @Column(name = "coins")
        private int coins = 0;
        @Column(name = "daily_date")
        private Long daily_date = 0L;
        @Transient
        private Inventory inventory = new Inventory(-1);

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            if (this.email.equals(email)) {
                return;
            }
            sendUpdateToMaster(this.id, "email", email);
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            if (this.password.equals(password)) {
                return;
            }
            sendUpdateToMaster(this.id, "password", password);
            this.password = password;
        }

        public Long getMoney() {
            return money;
        }

        public void setMoney(Long money) {
            if (this.money == money) {
                return;
            }
            sendUpdateToMaster(this.id, "money", money);
            this.money = money;
        }

        public String getFormattedName() {
            return formattedName;
        }

        public void setFormattedName(String formattedName) {
            if (this.formattedName.equals(formattedName)) {
                return;
            }
            sendUpdateToMaster(this.id, "formattedName", formattedName);
            this.formattedName = formattedName;
        }

        public String getSocialState() {
            return socialState;
        }

        public void setSocialState(String socialState) {
            if (this.socialState.equals(socialState)) {
                return;
            }
            sendUpdateToMaster(this.id, "socialState", socialState);
            this.socialState = socialState;
        }

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            if (this.groupId.equals(groupId)) {
                return;
            }
            sendUpdateToMaster(this.id, "groupId", groupId);
            this.groupId = groupId;
        }

        public Date getLastMessage() {
            return lastMessage;
        }

        public void setLastMessage(Date lastMessage) {
            this.lastMessage = lastMessage;
        }

        public int getWarnLevel() {
            return warnLevel;
        }

        public void setWarnLevel(int warnLevel) {
            this.warnLevel = warnLevel;
        }

        public Long getCreation_date() {
            return creation_date;
        }

        public void setCreation_date(Long creation_date) {
            if (this.creation_date == creation_date) {
                return;
            }
            sendUpdateToMaster(this.id, "creation_date", creation_date);
            this.creation_date = creation_date;
        }

        public Long getVerified_date() {
            return verified_date;
        }

        public void setVerified_date(Long verified_date) {
            if (this.verified_date == verified_date) {
                return;
            }
            sendUpdateToMaster(this.id, "verified_date", verified_date);
            this.verified_date = verified_date;
        }

        public int getAdminLevel() {
            return adminLevel;
        }

        public void setAdminLevel(int adminLevel) {
            if (this.adminLevel == adminLevel) {
                return;
            }
            sendUpdateToMaster(this.id, "adminLevel", adminLevel);
            this.adminLevel = adminLevel;
        }

        public boolean isVerified() {
            return isVerified;
        }

        public void setVerified(boolean verified) {
            if (this.isVerified == verified) {
                return;
            }
            sendUpdateToMaster(this.id, "isVerified", verified);
            this.isVerified = verified;
        }

        public String getVerified_name() {
            return verified_name;
        }

        public void setVerified_name(String verified_name) {
            if (this.verified_name.equals(verified_name)) {
                return;
            }
            sendUpdateToMaster(this.id, "verified_name", verified_name);
            this.verified_name = verified_name;
        }

        public int getVerified_age() {
            return verified_age;
        }

        public void setVerified_age(int verified_age) {
            if (this.verified_age == verified_age) {
                return;
            }
            sendUpdateToMaster(this.id, "verified_age", verified_age);
            this.verified_age = verified_age;
        }

        public String getVerified_city() {
            return verified_city;
        }

        public void setVerified_city(String verified_city) {
            if (this.verified_city.equals(verified_city)) {
                return;
            }
            sendUpdateToMaster(this.id, "verified_city", verified_city);
            this.verified_city = verified_city;
        }

        public String getVerified_picture() {
            return verified_picture;
        }

        public void setVerified_picture(String verified_picture) {
            if (this.verified_picture.equals(verified_picture)) {
                return;
            }
            sendUpdateToMaster(this.id, "verified_picture", verified_picture);
            this.verified_picture = verified_picture;
        }

        public Long getVerified_admin_id() {
            return verified_admin_id;
        }

        public void setVerified_admin_id(Long verified_admin_id) {
            if (this.verified_admin_id == verified_admin_id) {
                return;
            }
            sendUpdateToMaster(this.id, "verified_admin_id", verified_admin_id);
            this.verified_admin_id = verified_admin_id;
        }

        public String getVerified_admin_username() {
            return verified_admin_username;
        }

        public void setVerified_admin_username(String verified_admin_username) {
            if (this.verified_admin_username.equals(verified_admin_username)) {
                return;
            }
            sendUpdateToMaster(this.id, "verified_admin_username", verified_admin_username);
            this.verified_admin_username = verified_admin_username;
        }
        public String getLevel_background() {
            return level_background;
        }
        public void setLevel_background(String value) {
            if (level_background.equals(value)) return;
            sendUpdateToMaster(id, "level_background", value);
            level_background = value;
        }

        public String getMe_background() {
            return me_background;
        }
        public void setMe_background(String value) {
            if (me_background.equals(value)) return;
            sendUpdateToMaster(id, "me_background", value);
            me_background = value;
        }

        public Long getExp() {
            return exp;
        }
        public void setExp(Long value) {
            if (exp == value) return;
            sendUpdateToMaster(id, "exp", value);
            exp = value;
        }

        public String getLevelName() {
            return levelName;
        }
        public void setLevelName(String value) {
            if (levelName.equals(value)) return;
            sendUpdateToMaster(id, "levelName", value);
            levelName = value;
        }

        public int getVip_package() {
            return vip_package;
        }
        public void setVip_package(int value) {
            if (vip_package == value) return;
            sendUpdateToMaster(id, "vip_package", value);
            vip_package = value;
        }

        public Long getVip_till() {
            return vip_till;
        }
        public void setVip_till(Long value) {
            if (vip_till == value) return;
            sendUpdateToMaster(id, "vip_till", value);
            vip_till = value;
        }

        public String getLanguage() {
            return language;
        }
        public void setLanguage(String value) {
            if (language.equals(value)) return;
            sendUpdateToMaster(id, "language", value);
            language = value;
        }

        public int getCoins() {
            return coins;
        }
        public void setCoins(int value) {
            if (coins == value) return;
            sendUpdateToMaster(id, "coins", value);
            coins = value;
        }

        public Long getDaily_date() {
            return daily_date;
        }
        public void setDaily_date(Long value) {
            if (daily_date == value) return;
            sendUpdateToMaster(id, "daily_date", value);
            daily_date = value;
        }
        public Inventory getInventory() {
            return inventory;
        }
        public void setInventory(Inventory value) {
            inventory = value;
        }
}
