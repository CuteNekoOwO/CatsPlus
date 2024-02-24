package cuteneko.catsplus.utility;

import cuteneko.catsplus.CatsPlus;
import net.minecraft.util.Identifier;

public class Constants {
    public static final String TAG_CAT_CONTAINER = "Cat";

    public static final String TAG_ENTITY_POS = "Pos";
    public static final String TAG_CUSTOM_NAME = "CustomName";
    public static final String TAG_X = "x";
    public static final String TAG_Y = "y";
    public static final String TAG_Z = "z";
    public static final String TAG_UUID = "uuid";
    public static final String TAG_VALUE = "value";

    public static final String TAG_DEATH_TIME = "deathAt";
    public static final String TAG_DEATH_MESSAGE = "deathMessage";

    public static final Identifier CAP_CAT_PLAYER = new Identifier(CatsPlus.MODID, "cat_player_data");
    public static final String TAG_CAT_PLAYER_INNER_CAT = "innerCat";
    public static final String TAG_CAT_PLAYER_IS_CAT = "isCat";

    public static final Identifier TAG_SERVER_HAS_CAT = new Identifier(CatsPlus.MODID, "cat_server_data");
    public static final String TAG_SERVER_CAT_SPIRITS = "catSpirits";

    public static final Identifier CAP_GENIUS_CAT = new Identifier(CatsPlus.MODID, "genius_cat_data");
    public static final String TAG_GENIUS_CAT_LIVES = "lives";
    public static final String TAG_GENIUS_CAT_TOTEM = "totem";
    public static final String TAG_GENIUS_CAT_CAN_RESPAWN = "respawn";
    public static final String TAG_GENIUS_CAT_FAVORABILITY = "favorability";
    public static final String TAG_GENIUS_CAT_DANCING = "dancing";
    public static final String TAG_GENIUS_CAT_DANCING_SOURCE = "source";
    public static final String TAG_GENIUS_CAT_DANCING_SOUND_PLAYING = "soundPlaying";

    public static final String MESSAGE_CATS_GROUP_TITLE = "itemGroup.catsplus.catsplus_group";

    public static final String MESSAGE_CAT_BAG_DESCRIPTION_NO_CAT = "item.catsplus.cat_bag.desc.no_cat";
    public static final String MESSAGE_CAT_BAG_DESCRIPTION_HAS_CAT = "item.catsplus.cat_bag.desc.has_cat";
    public static final String MESSAGE_CAT_BAG_DESCRIPTION_HAS_NAMED_CAT = "item.catsplus.cat_bag.desc.has_named_cat";
    public static final String MESSAGE_FANG_LUO_DESCRIPTION_1 = "item.catsplus.fang_luo.desc.1";
    public static final String MESSAGE_FANG_LUO_DESCRIPTION_2 = "item.catsplus.fang_luo.desc.2";
    public static final String MESSAGE_CAT_SPIRIT_NAME = "item.catsplus.cat_spirit.with_name";
    public static final String MESSAGE_CAT_SPIRIT_DESCRIPTION_MESSAGE = "item.catsplus.cat_spirit.desc.message";
    public static final String MESSAGE_CAT_SPIRIT_DESCRIPTION_TIME_LABEL = "item.catsplus.cat_spirit.desc.time_label";
    public static final String MESSAGE_CAT_SPIRIT_DESCRIPTION_TIME_PATTERN = "item.catsplus.cat_spirit.desc.time_pattern";
    public static final String MESSAGE_CAT_SPIRIT_DESCRIPTION_TIME_LONG_TIME_AGO = "item.catsplus.cat_spirit.desc.long_time_ago";

    public static final String MESSAGE_CATTIFY_POTION = "item.minecraft.potion.effect.cattify";
    public static final String MESSAGE_CATTIFY_SPLASH_POTION = "item.minecraft.splash_potion.effect.cattify";
    public static final String MESSAGE_CATTIFY_LINGERING_POTION = "item.minecraft.lingering_potion.effect.cattify";
    public static final String MESSAGE_CATTIFY_POTION_ARROW = "item.minecraft.tipped_arrow.effect.cattify";

    public static final String MESSAGE_CAT_DIED = "message.catsplus.cat_died";
}
