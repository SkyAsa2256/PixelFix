package kr.icyonline.pixelfix.mixin;

import com.pixelmonmod.pixelmon.api.events.EvolveEvent;
import com.pixelmonmod.pixelmon.api.storage.PlayerPartyStorage;
import com.pixelmonmod.pixelmon.api.storage.StorageProxy;
import com.pixelmonmod.pixelmon.quests.exceptions.InvalidQuestArgsException;
import com.pixelmonmod.pixelmon.quests.listeners.EntityListeners;
import com.pixelmonmod.pixelmon.quests.objectives.Objectives;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = EntityListeners.class, remap = false)
public class MixinEntityListeners {

    /**
     * @author Asa
     * @reason Check the null of the entity.
     */
    @Overwrite
    @SubscribeEvent
    public void onEvolve(EvolveEvent.Pre event) throws InvalidQuestArgsException {
        ServerPlayerEntity player = event.getPlayer();

        if (player == null || event.getEntity() == null) {
            return;
        }

        PlayerPartyStorage pps = StorageProxy.getParty(player);
        pps.getQuestData(true).receive(Objectives.POKEMON_EVOLVE_PRE, event.getPokemon());
    }

    /**
     * @author Asa
     * @reason Check the null of the entity.
     */
    @Overwrite
    @SubscribeEvent
    public void onEvolve(EvolveEvent.Post event) throws InvalidQuestArgsException {
        ServerPlayerEntity player = event.getPlayer();

        if (player == null || event.getEntity() == null) {
            return;
        }

        PlayerPartyStorage pps = StorageProxy.getParty(player);
        pps.getQuestData(true).receive(Objectives.POKEMON_EVOLVE_POST, event.getPokemon());
    }


}