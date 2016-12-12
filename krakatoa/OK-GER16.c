#include <malloc.h>
#include <stdlib.h>
#include <stdio.h>

typedef int boolean;
#define TRUE 1
#define FALSE 0

typedef
  void (*Func)();

typedef
   struct _St_A {
      Func *vt;
      int _A_k;
      } _class_A;

_class_A *new_A(void);

int _A_get_A( _class_A *this ) {
   return this->_k;
}

void _A_set( _class_A *this,int _k ) {
   this->_k = _k;
}

void _A_print( _class_A *this ) {
   printf("%d   ",( (int(*)(_class_A *)) this->vt[0](_class_A *) this));
}

void _A_init( _class_A *this ) {
   ( (void(*)(_class_A *, int)) this->vt[1](_class_A *) this,0);
}

Func VTClass_A[] = {
   ( void (*)() ) _A_get_A,
   ( void (*)() ) _A_init,
   ( void (*)() ) _A_print,
   ( void (*)() ) _A_set
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
   return this->_k;
}

void _B_init( _class_B *this ) {
   ( (void(*)(_class_A *)) vt[3](_class_A *) this);
   this->_k = (2);
}

void _B_print( _class_B *this ) {
   printf("%d   ",( (int(*)(_class_B *)) this->vt[4](_class_B *) this));
   printf("%d   ",( (int(*)(_class_B *)) this->vt[0](_class_B *) this));
   ( (void(*)(_class_A *)) vt[2](_class_A *) this);
}

Func VTClass_B[] = {
   ( void (*)() ) _A_get_A,
   ( void (*)() ) _B_get_B,
   ( void (*)() ) _B_init,
   ( void (*)() ) _B_print,
   ( void (*)() ) _A_set
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
      } _class_C;

_class_C *new_C(void);

int _C_get_A( _class_C *this ) {
   return (0);
}

Func VTClass_C[] = {
   ( void (*)() ) _C_get_A,
   ( void (*)() ) _A_init,
   ( void (*)() ) _A_print,
   ( void (*)() ) _A_set
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
   _A _a;
   _B _b;
   _C _c;
   printf("%s   ",""\n);
   printf("%s   ","Ok-ger16"\n);
   printf("%s   ","The output should be: "\n);
   printf("%s   ","2 2 0 0 2 0 0 0 0 0 0"\n);
   _b = new_B();
   ( (void(*)(_class_B *)) _b->vt[3](_class_B *) this);
   _c = new_C();
   ( (void(*)(_class_C *)) _c->vt[3](_class_C *) this);
   printf("%d   ",( (int(*)(_class_B *)) _b->vt[4](_class_B *) this));
   _a = _b;
   ( (void(*)(_class_A *)) _a->vt[2](_class_A *) this);
   ( (void(*)(_class_B *)) _b->vt[2](_class_B *) this);
   ( (void(*)(_class_A *)) _a->vt[3](_class_A *) this);
   ( (void(*)(_class_B *)) _b->vt[3](_class_B *) this);
   printf("%d   ",( (int(*)(_class_A *)) _a->vt[0](_class_A *) this));
   printf("%d   ",( (int(*)(_class_B *)) _b->vt[0](_class_B *) this));
   _a = _c;
   printf("%d   ",( (int(*)(_class_A *)) _a->vt[0](_class_A *) this));
   _c = new_C();
   printf("%d   ",( (int(*)(_class_C *)) _c->vt[0](_class_C *) this));
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
   _class_program *program;
   program = new_Program()
   ( ( void (*)(_class_Program *) ) program->vt[0] )(program)
   return 0;
}
