# AAdapter
Add footer and header &amp; item click listener to recyclerview

**Project-level build.gradle** \(\<project>/build.gradle):
---
```
repositories {
	...
	maven { url 'https://jitpack.io' }
}
```
---
**App-level build.gradle** \(\<project>/\<app-module>/build.gradle):
---
```
compile compile 'com.github.mnayef:AAdapter:v1.0.1'
```

**JAVA** :
```java
recycler = (RecyclerView) findViewById(R.id.recycler);
footer = LayoutInflater.from(this).inflate(R.layout.footer, null);
header = LayoutInflater.from(this).inflate(R.layout.header, null);

aAdapter = new AAdapter(new TestAdapter());
aAdapter.addFooter(footer);
aAdapter.addHeader(header);

recycler.setLayoutManager(new LinearLayoutManager(this));
recycler.setAdapter(aAdapter);
```
