#include <malloc.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

typedef int boolean;
#define true 1
#define false 0

typedef
  void (*Func)();

typedef
   struct _St_A {
      Func *vt;
      int _A_n;
      } _class_A;

_class_A *new_A(void);

void _A_set( _class_A *this,int _n ) {
   this->_A_n = _n;
}

int _A_get( _class_A *this ) {
   return this->_A_n;
}

Func VTClass_A[] = {
   ( void (*)() ) _A_set,
   ( void (*)() ) _A_get
};

_class_A *new_A()
{
   _class_A *t;

   if ( (t = malloc(sizeof(_class_A))) != NULL )
      t->vt = VTClass_A;
   return t;
}

typedef
   struct _St_Program {
      Func *vt;
      _class_A* _Program_a;
      } _class_Program;

_class_Program *new_Program(void);

void _Program_print( _class_Program *this ) {
   printf("%d ", ( (int(*)(_class_A *)) this->_Program_a->vt[1])(this->_Program_a));
}

_class_A* _Program_get( _class_Program *this ) {
   return this->_Program_a;
}

void _Program_run( _class_Program *this ) {
   puts("");
   puts("Ok-ger21");
   puts("The output should be :");
   puts("0");
   puts("0");
}

void _Program_set( _class_Program *this,_class_A* _a ) {
   this->_Program_a = _a;
}

Func VTClass_Program[] = {
   ( void (*)() ) _Program_print,
   ( void (*)() ) _Program_get,
   ( void (*)() ) _Program_run
};

_class_Program *new_Program()
{
   _class_Program *t;

   if ( (t = malloc(sizeof(_class_Program))) != NULL )
      t->vt = VTClass_Program;
   return t;
}

int main() {
   _class_Program *program;
   program = new_Program();
   ( ( void (*)(_class_Program *) ) program->vt[2] )(program);
   return 0;
}
