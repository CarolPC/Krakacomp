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
      } _class_A;

_class_A *new_A(void);

void _A_run( _class_A *this ) {
}

void _A_m( _class_A *this ) {
   _class_A *_a;
   _a = (_class_A *)    NULL;
   ( (void(*)(_class_A *)) _a->vt[1])(_a);
}

Func VTClass_A[] = {
   ( void (*)() ) _A_run,
   ( void (*)() ) _A_m
};

_class_A *new_A()
{
   _class_A *t;

   if ( (t = malloc(sizeof(_class_A))) != NULL )
      t->vt = VTClass_A;
   return t;
}

typedef
   struct _St_B {
      Func *vt;
      } _class_B;

_class_B *new_B(void);

int _B_run( _class_B *this,int _n ) {
   return _n;
}

void _B_m( _class_B *this ) {
}

Func VTClass_B[] = {
   ( void (*)() ) _B_run,
   ( void (*)() ) _B_m
};

_class_B *new_B()
{
   _class_B *t;

   if ( (t = malloc(sizeof(_class_B))) != NULL )
      t->vt = VTClass_B;
   return t;
}

typedef
   struct _St_C {
      Func *vt;
      _class_B* _C_b;
      } _class_C;

_class_C *new_C(void);

void _C_m( _class_C *this ) {
   ( (void(*)(_class_B *)) this->_C_b->vt[1])(this->_C_b);
}

_class_B* _C_get( _class_C *this ) {
   return this->_C_b;
}

Func VTClass_C[] = {
   ( void (*)() ) _C_m,
   ( void (*)() ) _C_get
};

_class_C *new_C()
{
   _class_C *t;

   if ( (t = malloc(sizeof(_class_C))) != NULL )
      t->vt = VTClass_C;
   return t;
}

typedef
   struct _St_Program {
      Func *vt;
      } _class_Program;

_class_Program *new_Program(void);

void _Program_run( _class_Program *this ) {
   _class_C *_c;
   _c = (_class_C *)    NULL;
   ( (void(*)(_class_C *)) _c->vt[0])(_c);
}

Func VTClass_Program[] = {
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
   ( ( void (*)(_class_Program *) ) program->vt[0] )(program);
   return 0;
}
