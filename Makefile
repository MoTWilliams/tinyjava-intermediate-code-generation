CUP_RUNTIME := java-cup-11b-runtime.jar

BUILD_DIR := build
GEN_DIR := $(BUILD_DIR)/gen
CLASS_DIR := $(BUILD_DIR)/classes

JFLEX_FILE := TinyJava.jflex
CUP_FILE := TinyJava.cup
CODE_GEN_DRIVER := TinyJavaCodeGen
LEXER_GEN := TinyJavaLexer
PARSER_GEN := TinyJavaParser

%/:
	@mkdir -p $@

CLASS_PATH := .:$(GEN_DIR):$(CLASS_DIR):$(CUP_RUNTIME)

.DEFAULT_GOAL := build

build: lexer parser compile

lexer: $(GEN_DIR)/
	jflex -d $(GEN_DIR) $(JFLEX_FILE)

parser: $(GEN_DIR)/
	cup -parser $(PARSER_GEN) \
		-symbols Symbol \
		-destdir $(GEN_DIR) \
		$(CUP_FILE)

compile: lexer parser $(CLASS_DIR)/
	javac -cp $(CLASS_PATH) -d $(CLASS_DIR) $(GEN_DIR)/*.java *.java

generate_code: build
	java -cp $(CLASS_PATH) $(CODE_GEN_DRIVER) Test1
	java -cp $(CLASS_PATH) $(CODE_GEN_DRIVER) Test2
	java -cp $(CLASS_PATH) $(CODE_GEN_DRIVER) Test4
	java -cp $(CLASS_PATH) $(CODE_GEN_DRIVER) Test6

clean:
	rm -rf build