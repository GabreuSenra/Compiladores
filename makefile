JAVAC = javac

TEST_DIR = testes/sintaxe/certo

LAN_PATH = testes/semantica/certo/custom.lan

SRC = $(TEST_DIR)/$(LAN)

compile:
	javac -cp .:lib/antlr-4.13.2-complete.jar lang/**/*.java

run:
	java -cp .:lib/antlr-4.13.2-complete.jar lang.LangCompiler -i $(LAN_PATH)