package net.lyof.phantasm.block.challenge;

import com.google.gson.JsonObject;
import net.lyof.phantasm.Phantasm;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChallengeRegistry {
    private static final Map<Identifier, ChallengeData> REGISTRY = new HashMap<>();
    public static final ChallengeData EMPTY = new ChallengeData(Phantasm.makeID("/empty"), Phantasm.makeID("/empty"),
            List.of(), 0, 0, false);

    public static void register(Identifier id, ChallengeData data) {
        REGISTRY.putIfAbsent(id, data);
    }

    public static ChallengeData get(Identifier id) {
        if (!REGISTRY.containsKey(id)) Phantasm.log("Requesting ChallengeData for a non registered id: " + id, 2);
        return REGISTRY.getOrDefault(id, EMPTY);
    }

    public static void clear() {
        REGISTRY.clear();
    }
}
