package pwn.noobs.trouserstreak.modules;

import com.iekeiclient.engine.systems.modules.Module;
import pwn.noobs.trouserstreak.Trouser;

public class InvDupeModule extends Module {
    public InvDupeModule() {
        super(Trouser.Main, "1.17InventoryDupe", "InventoryDupe only works on servers with the version 1.17. (Not any version after or before.)");
    }
}