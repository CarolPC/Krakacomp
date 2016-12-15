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
      int _A_k;
      } _class_A;

_class_A *new_A(void);

int _A_get_A( _class_A *this ) {
   return this->_A_k;
}

void _A_init( _class_A *this ) {
   this->_A_k = (1);
}

Func VTClass_A[] = {
   ( void (*)() ) _A_get_A,
   ( void (*)() ) _A_init
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
      int _B_k;
      } _class_B;

_class_B *new_B(void);

int _B_get_B( _class_B *this ) {
   return this->_B_k;
}

void _B_init( _class_B *this ) {
   ( (void(*)(_class_A *)) vt[1])();
   this->_B_k = (2);
}

Func VTClass_B[] = {
   ( void (*)() ) _A_get_A,
   ( void (*)() ) _B_get_B,
   ( void (*)() ) _B_init
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
      int _C_k;
      } _class_C;

_class_C *new_C(void);

int _C_get_C( _class_C *this ) {
   return this->_C_k;
}

void _C_init( _class_C *this ) {
   ( (void(*)(_class_B *)) vt[1])();
   this->_C_k = (3);
}

Func VTClass_C[] = {
   ( void (*)() ) _A_get_A,
   ( void (*)() ) _B_get_B,
   ( void (*)() ) _C_get_C,
   ( void (*)() ) _C_init
};

_class_C *new_C()
{
   _class_C *t;

   if ( (t = malloc(sizeof(_class_C))) != NULL )
      t->vt = VTClass_C;
   return t;
}

typedef
   struct _St_D {
      Func *vt;
      int _D_k;
      } _class_D;

_class_D *new_D(void);

int _D_get_D( _class_D *this ) {
   return this->_D_k;
}

void _D_init( _class_D *this ) {
   ( (void(*)(_class_C *)) vt[1])();
   this->_D_k = (4);
}

Func VTClass_D[] = {
   ( void (*)() ) _A_get_A,
   ( void (*)() ) _B_get_B,
   ( void (*)() ) _C_get_C,
   ( void (*)() ) _D_get_D,
   ( void (*)() ) _D_init
};

_class_D *new_D()
{
   _class_D *t;

   if ( (t = malloc(sizeof(_class_D))) != NULL )
      t->vt = VTClass_D;
   return t;
}

typedef
   struct _St_Program {
      Func *vt;
      } _class_Program;

_class_Program *new_Program(void);

void _Program_run( _class_Program *this ) {
   _class_A* _a;
   _class_B* _b;
   _class_C* _c;
   _class_D* _d;
   puts("");
   puts("Ok-ger14");
   puts("The output should be :");
   puts("4 3 2 1");
   _d = new_D();
   ( (void(*)(_class_D *)) _d->vt[1])(_d);
   printf("%d", ( (int(*)(_class_D *)) _d->vt[1])(_d));
   _c = (_class_C *) _d;
   printf("%d", ( (int(*)(_class_C *)) _c->vt[1])(_c));
   _b = (_class_B *) _c;
   printf("%d", ( (int(*)(_class_B *)) _b->vt[2])(_b));
   _a = (_class_A *) _b;
   printf("%d", ( (int(*)(_class_A *)) _a->vt[0])(_a));
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
