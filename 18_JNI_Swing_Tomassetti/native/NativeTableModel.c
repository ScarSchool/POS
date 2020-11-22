
#include <jni.h>
#include "custom_NativeTableModel.h"
#include <string.h>
#include <stdlib.h>
#include "customList.h"

typedef enum PhoneType {Android, IPhone, Nokia} PhoneType;

int phoneIdx = 1;

struct Phone
{
	int index;
	long number;
	char* owner;
	int paid;
	PhoneType phoneType;
};

struct Column
{
	const char* title;
	const char* type;
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

jobject createJSTRING(JNIEnv *env,char *value)
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

jobject createJENUM(JNIEnv *env,PhoneType type)
{
	jclass enumClass = env->FindClass("models/PhoneType");
	jmethodID methodId = env->GetStaticMethodID(enumClass,"valueOf","(Ljava/lang/String;)Lmodels/PhoneType;");
	jstring value;
	switch (type)
	{
	case 0:
		value = env->NewStringUTF("ANDROID");
		break;
	case 1:
		value = env->NewStringUTF("IPHONE");
		break;
	case 2:
		value = env->NewStringUTF("NOKIA");
		break;
	}
	jobject enumvalue = env->CallStaticObjectMethod(enumClass, methodId, value);
	env->DeleteLocalRef(value);
	return enumvalue;
}

char *stringFromJSTRING(JNIEnv *env, jstring stringValue)
{
	const char *JStringChars = env->GetStringUTFChars(stringValue, JNI_FALSE);
	char * chars = (char *)malloc(sizeof(char) * (strlen(JStringChars) + 1));
	strcpy(chars, JStringChars);
	env->ReleaseStringUTFChars(stringValue, JStringChars);
	return chars;
}

customList_t* columns = init(); 

struct Column c1 = {"Index", "java/lang/Integer", JNI_FALSE};
struct Column c2 = {"Nummer", "java/lang/Long", JNI_TRUE};
struct Column c3 = {"Besitzer", "java/lang/String", JNI_TRUE};
struct Column c4 =  {"Bezahlt", "java/lang/Boolean", JNI_TRUE};
struct Column c5 = {"Typ", "models/PhoneType", JNI_TRUE};

customList_t* tableItems = init();

JNIEXPORT jint JNICALL
Java_custom_NativeTableModel_getRowCount(JNIEnv *env, jobject thisInstance)
{
	return tableItems->size;
}

JNIEXPORT jint JNICALL
Java_custom_NativeTableModel_getColumnCount(JNIEnv *env, jobject thisInstance)
{
	if(columns->size == 0){
		append(columns,&c1);
		append(columns,&c2);
		append(columns,&c3);
		append(columns,&c4);
		append(columns,&c5);
	}
	return columns->size;
}

JNIEXPORT jstring JNICALL
Java_custom_NativeTableModel_getColumnName(JNIEnv *env, jobject thisInstance, jint column)
{
	return env->NewStringUTF(((struct Column*)getAt(columns,column))->title);
}

JNIEXPORT jclass JNICALL
Java_custom_NativeTableModel_getColumnClass(JNIEnv *env, jobject thisInstance, jint column)
{
	return env->FindClass(((struct Column*)getAt(columns,column))->type);
}

JNIEXPORT jboolean JNICALL
Java_custom_NativeTableModel_isCellEditable(JNIEnv *env, jobject thisInstance, jint row, jint column)
{
	return ((struct Column*)getAt(columns,column))->isReadonly;
}

JNIEXPORT jobject JNICALL
Java_custom_NativeTableModel_getValueAt(JNIEnv *env, jobject thisInstance, jint row, jint column)
{
	struct Phone phone = *((struct Phone*)getAt(tableItems,row));
	switch (column)
	{
	case 0:
		return createJINT(env, phone.index);
	case 1:
		return createJLong(env, phone.number);
	case 2:
		return createJSTRING(env, phone.owner);
	case 3:
		return createJBOOLEAN(env, phone.paid);
	case 4:
		return createJENUM(env, phone.phoneType);
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
		((struct Phone*)getAt(tableItems,row))->index = env->CallIntMethod(object1, env->GetMethodID(env->FindClass("java/lang/Integer"), "intValue", "()I"));
		break;
	case 1:
		((struct Phone*)getAt(tableItems,row))->number = env->CallLongMethod(object1, env->GetMethodID(env->FindClass("java/lang/Long"), "longValue", "()J"));
		break;
	case 2:
	{
		((struct Phone*)getAt(tableItems,row))->owner = stringFromJSTRING(env,(jstring)object1);
		break;
	}
	case 3:
		((struct Phone*)getAt(tableItems,row))->paid = env->CallBooleanMethod(object1, env->GetMethodID(env->FindClass("java/lang/Boolean"), "booleanValue", "()Z"));
		break;
	case 4:
	char *enumCharValue = stringFromJSTRING(env, (jstring)env->CallObjectMethod(object1, env->GetMethodID(env->FindClass("models/PhoneType"), "name", "()Ljava/lang/String;")));
	if (strcmp(enumCharValue, "ANDROID") == 0)
	{
		((struct Phone*)getAt(tableItems,row))->phoneType = (PhoneType)0;
	}
	else if (strcmp(enumCharValue, "IPHONE") == 0)
	{
		((struct Phone*)getAt(tableItems,row))->phoneType = (PhoneType)1;
	}
	else
	{
		((struct Phone*)getAt(tableItems,row))->phoneType = (PhoneType)2;
	}
	break;
	}
}

long getNumber(JNIEnv *env, jclass phoneClass, jobject phone){
	return env->CallLongMethod(phone, env->GetMethodID(phoneClass, "getNumber", "()J"));
}

int getPaid(JNIEnv *env, jclass phoneClass, jobject phone)
{
	return env->CallBooleanMethod(phone, env->GetMethodID(phoneClass, "getPaid", "()Z"));
}

char *getOwner(JNIEnv *env, jclass phoneClass, jobject phone){
	jstring value = (jstring)env->CallObjectMethod(phone, env->GetMethodID(phoneClass, "getOwner", "()Ljava/lang/String;"));
	return stringFromJSTRING(env,value);
}

PhoneType getType(JNIEnv *env, jclass phoneClass, jclass phoneEnumClass, jobject phone)
{
	jobject enumValue = env->CallObjectMethod(phone, env->GetMethodID(phoneClass, "getPhoneType", "()Lmodels/PhoneType;"));

	jstring value = (jstring)env->CallObjectMethod(enumValue, env->GetMethodID(phoneEnumClass, "name", "()Ljava/lang/String;"));
	char* enumCharValue = stringFromJSTRING(env,value);
	if (strcmp(enumCharValue, "ANDROID")==0)
	{
		return Android;
	}
	else if (strcmp(enumCharValue, "IPHONE")==0)
	{
		return IPhone;
	}
	else
	{
		return Nokia;
	}
}

JNIEXPORT void JNICALL
Java_custom_NativeTableModel_addItem(JNIEnv *env, jobject thisInstance, jobject phone)
{
	jclass classPhone = env->FindClass("models/Phone");
	jclass classPhoneEnum = env->FindClass("models/PhoneType");
	struct Phone *p = (struct Phone *)malloc(sizeof(struct Phone));
	p->index = phoneIdx++;
	p->number = getNumber(env, classPhone, phone);
	p->owner = getOwner(env, classPhone, phone);
	p->paid = getPaid(env, classPhone, phone);
	p->phoneType = getType(env,classPhone,classPhoneEnum,phone);
	append(tableItems,p);
	jclass classModel = env->FindClass("custom/NativeTableModel");
	jmethodID reload = env->GetMethodID(classModel, "fireTableDataChanged", "()V");
	env->CallObjectMethod(thisInstance, reload);
}

JNIEXPORT void JNICALL
Java_custom_NativeTableModel_removeRow(JNIEnv * env, jobject thisInstance, jint index){
	deleteAt(tableItems,index);
	jclass classModel = env->FindClass("custom/NativeTableModel");
	jmethodID reload = env->GetMethodID(classModel, "fireTableDataChanged", "()V");
	env->CallObjectMethod(thisInstance, reload);
}

struct Phone * createPhoneFromLine(char* line)
{
	long int number;
	int paid; 
	int type;
	char* name = (char*)malloc(sizeof(char)*100);
	sscanf(line,"%ld;%s ;%d;%d",&number,name,&paid,&type);
	struct Phone *p = (struct Phone *)malloc(sizeof(struct Phone));
	p->index = phoneIdx++;
	p->number = number;
	p->owner = name;
	p->paid = paid;
	p->phoneType = (PhoneType)type;
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
			append(tableItems, createPhoneFromLine(buffer));
			buffer = (char *)calloc(CURRENT_MAX,sizeof(char));
			length = 0;
			if(ch == EOF) return;
			else ch = getc(fp);
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
Java_custom_NativeTableModel_loadFromFile(JNIEnv * env, jobject thisInstance, jstring path){
	char const *const fileName = stringFromJSTRING(env, path); /* should check that argc > 1 */
	FILE *file = fopen(fileName, "r");	  /* should check the result */
	if(file == NULL){
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
Java_custom_NativeTableModel_saveToFile(JNIEnv * env, jobject thisInstance, jstring path)
{
	FILE *file = fopen(stringFromJSTRING(env, path),"w");
	if (file == NULL)
	{
		printf("ERROR: Could not open file with name %s", stringFromJSTRING(env, path));
		return;
	}
	for(int i = 0; i < tableItems->size; i++){
		struct Phone* p = (struct Phone*)getAt(tableItems,i);
		fprintf(file,"%ld;%s ;%d;%d\n",p->number,p->owner,p->paid,p->phoneType);
	}
	fclose(file);
}