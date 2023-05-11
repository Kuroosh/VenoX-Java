package venox.bot.models;

public class Session {
    public Session(String name, long number, boolean isRegistered){
        this.Name = name;
        this.Number = number;
        this.IsRegistered = isRegistered;
    }
    public String Name;
    public long Number;

    public boolean IsRegistered;
}
