# ManasMods Modding Template
This is a simple template to create new Mods using ManasCore mods without the need to always setup gradle and stuff.

## Things you need to change

File: `gradle.properties`

Replace `archives_name = manas_template` with `archives_name = ` followed by your mod id.

Replace `mod_display_name = ManasMods 1.21 Template` with `mod_display_name = ` followed by your mod display name

---

Path: `common/src/main/resources`

File: `manas_template.mixins.json`

Rename file to your mod id `.mixins.json`

Change the path of `"package": "com.github.manasmods.template.mixin",` to the path where your mixins are located.

Replace `template` in `"refmap": "template.refmap.json",` with your mod id

---

Path: `common/src/main/java/io/github/manasmods`

Rename the Folder `template` to your mod id

---

Path: `common/src/main/java/io/github/manasmods/<your mod id>`

File: `Template.java`

Rename `Template.java` to your main class name

Replace `template` in `public static final String MOD_ID = "manas_template"; //TODO replace template with your mod id` with your mod id and remove the "TODO" comment behind the `;`

---

Path: `common/src/main/resources`

File: `manas_template.accesswidener`

Rename file to your mod id `.accesswidener`

---

Path: `common/src/main/resources`

File: `architectury.common.json`

Replace `manas_template` in `"accessWidener": "manas_template.accesswidener"` with your mod id

---

Path: `fabric/src/main/java/io/github/manasmods`

Rename the Folder `template` to your mod id

---

Path: `fabric/src/main/resources`

File: `fabric.mod.json`

Replace Template mod id with the id of your Mod on line 3

Replace Template name with the name of your Mod on line 5

Provide a description of your Mod on line 6

Provide a path for the icon of your Mod on line 15

Provide proper paths for the entry points of your Mod on line 17-24

---

Path: `neoforge/src/main/java/io/github/manasmods`

Rename the Folder `template` to your mod id

---

Path: `neoforge/src/main/resources/META-INF`

File: `neoforge.mods.toml`

Replace Template mod id with the id of your Mod on line 7

Replace Template display name with the name of your Mod on line 9

Provide a description of your Mod on line 11

---