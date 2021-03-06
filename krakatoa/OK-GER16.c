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

void _A_set( _class_A *this,int _k ) {
   this->_A_k = _k;
}

void _A_print( _class_A *this ) {
   printf("%d ", ( (int(*)(_class_A *)) this->vt[0])(this));
}

void _A_init( _class_A *this ) {
   ( (void(*)(_class_A *, int)) this->vt[1])(this,0);
}

Func VTClass_A[] = {
   ( void (*)() ) _A_get_A,
   ( void (*)() ) _A_set,
   ( void (*)() ) _A_print,
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
      int _A_k;
      int _B_k;
      } _class_B;

_class_B *new_B(void);

int _B_get_B( _class_B *this ) {
   return this->_B_k;
}

void _B_init( _class_B *this ) {
   _A_init((_class_A*)this);
   this->_B_k = (2);
}

void _B_print( _class_B *this ) {
   printf("%d ", ( (int(*)(_class_B *)) this->vt[4])(this));
   printf("%d ", ( (int(*)(_class_B *)) this->vt[0])(this));
   _A_print((_class_A*)this);
}

Func VTClass_B[] = {
   ( void (*)() ) _A_get_A,
   ( void (*)() ) _A_set,
   ( void (*)() ) _B_print,
   ( void (*)() ) _B_init,
   ( void (*)() ) _B_get_B
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
      int _A_k;
      } _class_C;

_class_C *new_C(void);

int _C_get_A( _class_C *this ) {
   return (0);
}

Func VTClass_C[] = {
   ( void (*)() ) _C_get_A,
   ( void (*)() ) _A_set,
   ( void (*)() ) _A_print,
   ( void (*)() ) _A_init
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
   _class_A *_a;
   _class_B *_b;
   _class_C *_c;
   puts("");
   puts("Ok-ger16");
   puts("The output should be: ");
   puts("2 2 0 0 2 0 0 0 0 0 0");
   _b = new_B();
   ( (void(*)(_class_B *)) _b->vt[3])(_b);
   _c = new_C();
   ( (void(*)(_class_C *)) _c->vt[3])(_c);
   printf("%d ", ( (int(*)(_class_B *)) _b->vt[4])(_b));
   _a = (_class_A *) _b;
   ( (void(*)(_class_A *)) _a->vt[2])(_a);
   ( (void(*)(_class_B *)) _b->vt[2])(_b);
   ( (void(*)(_class_A *)) _a->vt[3])(_a);
   ( (void(*)(_class_B *)) _b->vt[3])(_b);
   printf("%d ", ( (int(*)(_class_A *)) _a->vt[0])(_a));
   printf("%d ", ( (int(*)(_class_B *)) _b->vt[0])(_b));
   _a = (_class_A *) _c;
   printf("%d ", ( (int(*)(_class_A *)) _a->vt[0])(_a));
   _c = new_C();
   printf("%d ", ( (int(*)(_class_C *)) _c->vt[0])(_c));
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
