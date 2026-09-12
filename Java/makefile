JC = javac

JAR_DIR = jar
SRC_DIR = src
TEST_DIR = test
CLASSES_DIR = classes

SOURCES = $(shell find $(SRC_DIR) -name "*.java")
TEST_SOURCES = $(shell find $(TEST_DIR) -name "*.java")

.PHONY: all docs classes jar tests runtests clean

all: classes

doc: classes
	mkdir -p docs
	javadoc -sourcepath src -subpackages btd -d docs -Xdoclint:none -quiet 

classes:
	mkdir -p $(CLASSES_DIR)
	$(JC) -d $(CLASSES_DIR) $(SOURCES)

jar: classes
	mkdir -p $(JAR_DIR)
	# JAR 1 : Plateau A - Interactif
	jar cfe $(JAR_DIR)/towerdefense-a-interactive.jar btd.utils.MainClassicInteractive -C $(CLASSES_DIR) .
	
	# JAR 2 : Plateau B - Interactif
	jar cfe $(JAR_DIR)/towerdefense-b-interactive.jar btd.utils.MainLinearInteractive -C $(CLASSES_DIR) .
	
	# JAR 3 : Plateau A - Aléatoire
	jar cfe $(JAR_DIR)/towerdefense-a-random.jar btd.utils.MainClassicRandom -C $(CLASSES_DIR) .
	
	# JAR 4 : Plateau B - Aléatoire
	jar cfe $(JAR_DIR)/towerdefense-b-random.jar btd.utils.MainLinearRandom -C $(CLASSES_DIR) .
tests: classes
	$(JC) -classpath junit-console.jar:$(CLASSES_DIR) $(TEST_SOURCES) -d $(CLASSES_DIR)

runtests: tests
	java -jar junit-console.jar -classpath $(TEST_DIR):$(CLASSES_DIR) -scan-classpath


clean:
	rm -rf $(CLASSES_DIR) docs
