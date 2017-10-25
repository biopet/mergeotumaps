# Manual

MergeOtuMap takes several OTU maps and merges them in a single file.
You can ignore certain prefixes in the OTU maps so these entries are not 
included in the output file.

Example:
```bash
java -jar MergeOtuMaps-version.jar \
-I OtuMap1 \
-I OtuMap2 \
-o MergedOtuMpas \
-p seq8
```
