<h4>The what</h4>
Q. What is this project?
<br>A. This is a gradle project and has a few custom JMeter Java Samplers not provided by JMeter.
<br>
<br>Q. What are the prerequisites?
<br>A. Use JMeter 2.9+ and Java 1.5+.
<br>
<br>Q. What custom tasks does this project provide?
<br>A. The following tasks are availabele
<ul>
  <li>A file copier - which works with a source file and a destination file (both absolute).</li>
  <li>A file verifier - which accepts an absolute path of a file and checks if it exists are not.</li>
</ul>
<h4>The how</h4>
Q. How to use this in JMeter?
<br>A. Steps involved are as follows
<ul>
<li>Execute "./gradlew clean jar" to generate a jar file in build/libs</li>
<li>Copy the jar from build/libs to the JMeter's lib/ext/ folder</li>
<li>Open JMeter</li>
<li>Add a "Sampler" --> "Java Request"</li>
<li>In the drop down for "Classname" you should see the custom class names that are available,
in this case "JavaFileCopier" and "JavaFileExistenceVerifier"</li>
</ul>
Q. How to write a new custom JMeter task?
<br>A. Follow these steps.
<ul>
<li>Write a class and implement org.apache.jmeter.protocol.java.sampler.JavaSamplerClient</li>
<li>Test what ever you want and set the org.apache.jmeter.samplers.SampleResult appropriately</li>
<li>Create a jar, copy it and restart JMeter, you should be all set!</li>
</ul>
<h4>Gradle information</h4>
Q. How to install Gradle?
<br>A. Gradle away! It is available with the source code. Use the gradlew/gradlew.bat scripts based on environment to execute tasks.
<br>
<br>Q. What is meant by "Gradle is already available"?
<br>A. The following files and folders are part of the source code.
<ul>
<li>gradle - a directory</li>
<li>gradlew - a script for *nix environments</li>
<li>gradlew.bat - a script for Windows environment</li>
</ul>
A couple of questions should be answered.
<ul>
<li>Where did the above files and folder come from? - build.gradle has a task - called "wrapper", which upon execution, "gradle wrapper", downloaded the above. The above files being available means "Gradle is available". </li>
<li>How to run a gradle task? - Use the ./gradlew or .bat file according to your environment. On Mac OSX, to run all the unit tests, run "./gradle test"</li>
</ul>
<b>All gradle commands below were executed on a Mac OSX. Use the .bat variant if you are working on a Windows environment</b>
<br>Q. How to create an Intellij Idea project?
<ul>
<li>Execute "./gradlew clean cleanIdea idea"</li>
<li>Open Intellij Idea and use the "Open Project" prompts to import the project.</li>
</ul>
Q. What are some helpful commands in gradle?
<br>A. Execute "./gradlew tasks" for a list of available tasks
<br>
<br>Q. Intellij Idea is not able to resolve dependencies though they are available in the local cache. What is wrong?
<br>A. For some reason, Intellij Idea 10.5 does not auto refresh when a new dependency is added to gradle.build. As a workaround, do the following
<ul>
<li>Close Intellij Idea</li>
<li>./gradlew cleanIdea</li>
<li>./gradlew clean idea</li>
<li>Reopen the idea project - the dependency should be available.</li>
</ul>
Q. How to edit this md file?
<br>A. This md file was edited using http://notepag.es/, which is no longer available (as of Feb 01, 2013). Try http://dfilimonov.com/github-markup-preview/
<br><h4>References</h4>
<ul>
<li>http://www.javacodegeeks.com/2012/05/apache-jmeter-load-test-whatever-you.html</li>
</ul>