# ManasMods Modding Template
This is a simple template to create new Mods without the need to always setup gradle and stuff.

## Things you need to change

File: `build.gradle`

Replace `template` with your Mod Id on the following lines and remove the "TODO" line above

Lines:
- 42
- 56
- 70
- 84

---

File: `gradle.properties`

Replace `modId=mod_id` with `modId=` followed by your mod id.

Replace `githubUrl=https://github.com/ManasMods/PROJECT_NAME` with `githubUrl=` followed by your GitHub Repository URL

Replace `modName=Template Mod` with `modName=` followed by your mod display name

---

Path: `src/main/resources/META-INF`

File: `mods.toml`

Provide a description of your Mod on line 17

---

Path: `src/main/resources`

File: `template.mixins.json`

Rename file to your mod id `.mixins.json`

Change the path of `"package": "com.github.manasmods.template.mixin",` to the path where your mixins are located.

Replace `template` in `"refmap": "template.refmap.json",` with your mod id

---

Path: `src/main/java/com/github/manasmods`

Rename the Folder `template` to your mod id

---

Path: `src/main/java/com/github/manasmods/<your mod id>`

File: `Template.java`

Rename `Template.java` to your main class name

Replace `template` in `public static final String MOD_ID = "template"; //TODO replace template with your mod id` with your mod id and remove the "TODO" comment behind the `;`

---

