
#include <jni.h>
#include "custom_NativeTableModel.h"
#include <string.h>
#include <stdlib.h>
#include "customList.h"

typedef enum BottleType
{
	Metall,
	Glass,
	Wood
} BottleType;

int bottleIdx = 1;

struct Bottle
{
	int index;
	char *material;
	char *owner;
	int filled;
	BottleType bottleType;
};

struct Column
{
	const char *title;
	const char *type;
	int isReadonly;
};

jobject createJINT(JNIEnv *env, int number)
{
	jclass Integer = env->FindClass("java/lang/Integer");
	jobject myInteger = env->AllocObject(Integer);
	jmethodID init = env->GetMethodID(Integer, "<init>", "(I)V");
	env->CallVoidMethod(myInteger, init, number);
	return myInteger;
}

jobject createJLong(JNIEnv *env, long number)
{
	jclass Integer = env->FindClass("java/lang/Long");
	jobject myLong = env->AllocObject(Integer);
	jmethodID init = env->GetMethodID(Integer, "<init>", "(J)V");
	env->CallVoidMethod(myLong, init, number);
	return myLong;
}

jobject createJSTRING(JNIEnv *env, char *value)
{
	return env->NewStringUTF(value);
}

jobject createJBOOLEAN(JNIEnv *env, int value)
{
	jclass Boolean = env->FindClass("java/lang/Boolean");
	jobject myBool = env->AllocObject(Boolean);
	jmethodID init = env->GetMethodID(Boolean, "<init>", "(Z)V");
	env->CallVoidMethod(myBool, init, value);
	return myBool;
}

jobject createJENUM(JNIEnv *env, BottleType type)
{
	jclass enumClass = env->FindClass("models/BottleType");
	jmethodID methodId = env->GetStaticMethodID(enumClass, "valueOf", "(Ljava/lang/String;)Lmodels/BottleType;");
	jstring value;
	switch (type)
	{
	case 0:
		value = env->NewStringUTF("METALL");
		break;
	case 1:
		value = env->NewStringUTF("GLASS");
		break;
	case 2:
		value = env->NewStringUTF("WOOD");
		break;
	}
	jobject enumvalue = env->CallStaticObjectMethod(enumClass, methodId, value);
	env->DeleteLocalRef(value);
	return enumvalue;
}

char *stringFromJSTRING(JNIEnv *env, jstring stringValue)
{
	const char *JStringChars = env->GetStringUTFChars(stringValue, JNI_FALSE);
	char *chars = (char *)malloc(sizeof(char) * (strlen(JStringChars) + 1));
	strcpy(chars, JStringChars);
	env->ReleaseStringUTFChars(stringValue, JStringChars);
	return chars;
}

customList_t *columns = init();

struct Column c1 = {"Index", "java/lang/Integer", JNI_FALSE};
struct Column c2 = {"Material", "java/lang/String", JNI_TRUE};
struct Column c3 = {"Besitzer", "java/lang/String", JNI_TRUE};
struct Column c4 = {"Gefuellt", "java/lang/Boolean", JNI_TRUE};
struct Column c5 = {"Typ", "models/BottleType", JNI_TRUE};

customList_t *tableItems = init();

JNIEXPORT jint JNICALL
Java_custom_NativeTableModel_getRowCount(JNIEnv *env, jobject thisInstance)
{
	return tableItems->size;
}

JNIEXPORT jint JNICALL
Java_custom_NativeTableModel_getColumnCount(JNIEnv *env, jobject thisInstance)
{
	if (columns->size == 0)
	{
		append(columns, &c1);
		append(columns, &c2);
		append(columns, &c3);
		append(columns, &c4);
		append(columns, &c5);
	}
	return columns->size;
}

JNIEXPORT jstring JNICALL
Java_custom_NativeTableModel_getColumnName(JNIEnv *env, jobject thisInstance, jint column)
{
	return env->NewStringUTF(((struct Column *)getAt(columns, column))->title);
}

JNIEXPORT jclass JNICALL
Java_custom_NativeTableModel_getColumnClass(JNIEnv *env, jobject thisInstance, jint column)
{
	return env->FindClass(((struct Column *)getAt(columns, column))->type);
}

JNIEXPORT jboolean JNICALL
Java_custom_NativeTableModel_isCellEditable(JNIEnv *env, jobject thisInstance, jint row, jint column)
{
	return ((struct Column *)getAt(columns, column))->isReadonly;
}

JNIEXPORT jobject JNICALL
Java_custom_NativeTableModel_getValueAt(JNIEnv *env, jobject thisInstance, jint row, jint column)
{
	struct Bottle bottle = *((struct Bottle *)getAt(tableItems, row));
	switch (column)
	{
	case 0:
		return createJINT(env, phbottleone.index);
	case 1:
		return createJSTRING(env, bottle.material);
	case 2:
		return createJSTRING(env, bottle.owner);
	case 3:
		return createJBOOLEAN(env, bottle.filled);
	case 4:
		return createJENUM(env, bottle.bottleType);
	default:
		printf("ERROR: INVALID INDEX\n");
		return NULL;
		//TODO: Find out how to handle errors in JNI lol
	}
}

JNIEXPORT void JNICALL
Java_custom_NativeTableModel_setValueAt(JNIEnv *env, jobject thisInstance, jobject object1, jint row, jint column)
{
	switch ((int)column)
	{
	case 0:
		((struct Bottle *)getAt(tableItems, row))->index = env->CallIntMethod(object1, env->GetMethodID(env->FindClass("java/lang/Integer"), "intValue", "()I"));
		break;
	case 1:
		((struct Bottle *)getAt(tableItems, row))->material = env->CallLongMethod(object1, env->GetMethodID(env->FindClass("java/lang/Long"), "longValue", "()J"));
		break;
	case 2:
	{
		((struct Bottle *)getAt(tableItems, row))->owner = stringFromJSTRING(env, (jstring)object1);
		break;
	}
	case 3:
		((struct Bottle *)getAt(tableItems, row))->filled = env->CallBooleanMethod(object1, env->GetMethodID(env->FindClass("java/lang/Boolean"), "booleanValue", "()Z"));
		break;
	case 4:
		char *enumCharValue = stringFromJSTRING(env, (jstring)env->CallObjectMethod(object1, env->GetMethodID(env->FindClass("models/BottleType"), "name", "()Ljava/lang/String;")));
		if (strcmp(enumCharValue, "METALL") == 0)
		{
			((struct Bottle *)getAt(tableItems, row))->bottleType = (BottleType)0;
		}
		else if (strcmp(enumCharValue, "GLASS") == 0)
		{
			((struct Bottle *)getAt(tableItems, row))->bottleType = (BottleType)1;
		}
		else
		{
			((struct Bottle *)getAt(tableItems, row))->bottleType = (BottleType)2;
		}
		break;
	}
}

long getMaterial(JNIEnv *env, jclass bottleClass, jobject bottle)
{
	return env->CallLongMethod(bottle, env->GetMethodID(bottleClass, "getMaterial", "()J"));
}

int getFilled(JNIEnv *env, jclass bottleClass, jobject bottle)
{
	return env->CallBooleanMethod(bottle, env->GetMethodID(bottleClass, "getFilled", "()Z"));
}

char *getOwner(JNIEnv *env, jclass bottleClass, jobject bottle)
{
	jstring value = (jstring)env->CallObjectMethod(bottle, env->GetMethodID(bottleClass, "getOwner", "()Ljava/lang/String;"));
	return stringFromJSTRING(env, value);
}

BottleType getType(JNIEnv *env, jclass bottleClass, jclass bottleEnumClass, jobject bottle)
{
	jobject enumValue = env->CallObjectMethod(bottle, env->GetMethodID(bottleClass, "getbottleType", "()Lmodels/BottleType;"));

	jstring value = (jstring)env->CallObjectMethod(enumValue, env->GetMethodID(bottleEnumClass, "name", "()Ljava/lang/String;"));
	char *enumCharValue = stringFromJSTRING(env, value);
	if (strcmp(enumCharValue, "METALL") == 0)
	{
		return Metall;
	}
	else if (strcmp(enumCharValue, "Glass") == 0)
	{
		return Glass;
	}
	else
	{
		return Wood;
	}
}

JNIEXPORT void JNICALL
Java_custom_NativeTableModel_addItem(JNIEnv *env, jobject thisInstance, jobject bottle)
{
	jclass classbottle = env->FindClass("models/Bottle");
	jclass classbottleEnum = env->FindClass("models/BottleType");
	struct Bottle *p = (struct Bottle *)malloc(sizeof(struct Bottle));
	p->index = bottleIdx++;
	p->material = getMaterial(env, classbottle, bottle);
	p->owner = getOwner(env, classbottle, bottle);
	p->filled = getFilled(env, classbottle, bottle);
	p->bottleType = getType(env, classbottle, classbottleEnum, bottle);
	append(tableItems, p);
	jclass classModel = env->FindClass("custom/NativeTableModel");
	jmethodID reload = env->GetMethodID(classModel, "fireTableDataChanged", "()V");
	env->CallObjectMethod(thisInstance, reload);
}

JNIEXPORT void JNICALL
Java_custom_NativeTableModel_removeRow(JNIEnv *env, jobject thisInstance, jint index)
{
	deleteAt(tableItems, index);
	jclass classModel = env->FindClass("custom/NativeTableModel");
	jmethodID reload = env->GetMethodID(classModel, "fireTableDataChanged", "()V");
	env->CallObjectMethod(thisInstance, reload);
}

struct Bottle *createbottleFromLine(char *line)
{
	long int material;
	int filled;
	int type;
	char *name = (char *)malloc(sizeof(char) * 100);
	sscanf(line, "%ld;%s ;%d;%d", &material, name, &filled, &type);
	struct Bottle *p = (struct Bottle *)malloc(sizeof(struct Bottle));
	p->index = bottleIdx++;
	p->material = material;
	p->owner = name;
	p->filled = filled;
	p->bottleType = (BottleType)type;
	return p;
}

void processFile(FILE *fp)
{
	int NORMAL_MAX = 1000;
	int CURRENT_MAX = 1000;
	char *buffer = (char *)malloc(sizeof(char) * NORMAL_MAX);
	int length = 0;
	int ch = getc(fp);
	while (ch != EOF)
	{
		if (ch == '\n' || ch == EOF)
		{
			CURRENT_MAX = NORMAL_MAX;
			append(tableItems, createbottleFromLine(buffer));
			buffer = (char *)calloc(CURRENT_MAX, sizeof(char));
			length = 0;
			if (ch == EOF)
				return;
			else
				ch = getc(fp);
		}
		buffer[length] = ch;
		length++;
		if (length == CURRENT_MAX)
		{
			CURRENT_MAX *= 1.5;
			buffer = (char *)realloc(buffer, CURRENT_MAX);
		}
		ch = getc(fp);
	}
	free(buffer);
}

JNIEXPORT void JNICALL
Java_custom_NativeTableModel_loadFromFile(JNIEnv *env, jobject thisInstance, jstring path)
{
	char const *const fileName = stringFromJSTRING(env, path); /* should check that argc > 1 */
	FILE *file = fopen(fileName, "r");						   /* should check the result */
	if (file == NULL)
	{
		printf("ERROR: Could not open file with name %s", fileName);
		return;
	}
	processFile(file);
	fclose(file);
	jclass classModel = env->FindClass("custom/NativeTableModel");
	jmethodID reload = env->GetMethodID(classModel, "fireTableDataChanged", "()V");
	env->CallObjectMethod(thisInstance, reload);
}

JNIEXPORT void JNICALL
Java_custom_NativeTableModel_saveToFile(JNIEnv *env, jobject thisInstance, jstring path)
{
	FILE *file = fopen(stringFromJSTRING(env, path), "w");
	if (file == NULL)
	{
		printf("ERROR: Could not open file with name %s", stringFromJSTRING(env, path));
		return;
	}
	for (int i = 0; i < tableItems->size; i++)
	{
		struct Bottle *p = (struct Bottle *)getAt(tableItems, i);
		fprintf(file, "%ld;%s ;%d;%d\n", p->material, p->owner, p->filled, p->bottleType);
	}
	fclose(file);
}