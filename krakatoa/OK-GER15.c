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
      int _A_i;
      int _A_j;
      } _class_A;

_class_A *new_A(void);

void _A_init_A( _class_A *this ) {
   this->_i = (1);
   this->_j = (2);
}

void _A_call_p( _class_A *this ) {
   _A_p(this);
}

void _A_call_q( _class_A *this ) {
   _A_q(this);
}

void _A_r( _class_A *this ) {
   printf("%d   ",this->_i);
}

void _A_s( _class_A *this ) {
   printf("%d   ",this->_j);
}

void _A_p( _class_A *this ) {
   printf("%d   ",this->_i);
}

void _A_q( _class_A *this ) {
   printf("%d   ",this->_j);
}

Func VTClass_A[] = {
   ( void (*)() ) _A_call_p,
   ( void (*)() ) _A_call_q,
   ( void (*)() ) _A_init_A,
   ( void (*)() ) _A_r,
   ( void (*)() ) _A_s
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
      int _B_i;
      int _B_j;
      } _class_B;

_class_B *new_B(void);

void _B_init_B( _class_B *this ) {
   this->_i = (3);
   this->_j = (4);
}

void _B_call_p( _class_B *this ) {
   _B_p(this);
}

void _B_call_q( _class_B *this ) {
   _B_q(this);
}

void _B_r( _class_B *this ) {
   printf("%d   ",this->_i);
}

void _B_s( _class_B *this ) {
   printf("%d   ",this->_j);
}

void _B_p( _class_B *this ) {
   printf("%d   ",this->_i);
}

void _B_q( _class_B *this ) {
   printf("%d   ",this->_j);
}

Func VTClass_B[] = {
   ( void (*)() ) _B_call_p,
   ( void (*)() ) _B_call_q,
   ( void (*)() ) _A_init_A,
   ( void (*)() ) _B_init_B,
   ( void (*)() ) _B_r,
   ( void (*)() ) _B_s
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
      int _C_i;
      int _C_j;
      } _class_C;

_class_C *new_C(void);

void _C_init_C( _class_C *this ) {
   this->_i = (5);
   this->_j = (6);
}

void _C_call_p( _class_C *this ) {
   _C_p(this);
}

void _C_call_q( _class_C *this ) {
   _C_q(this);
}

void _C_r( _class_C *this ) {
   printf("%d   ",this->_i);
}

void _C_s( _class_C *this ) {
   printf("%d   ",this->_j);
}

void _C_p( _class_C *this ) {
   printf("%d   ",this->_i);
}

void _C_q( _class_C *this ) {
   printf("%d   ",this->_j);
}

Func VTClass_C[] = {
   ( void (*)() ) _C_call_p,
   ( void (*)() ) _C_call_q,
   ( void (*)() ) _A_init_A,
   ( void (*)() ) _C_init_C,
   ( void (*)() ) _C_r,
   ( void (*)() ) _C_s
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
   printf("%s   ","Ok-ger15"\n);
   printf("%s   ","The output should be :"\n);
   printf("%s   ","1 2 1 2 3 4 3 4 5 6 5 6"\n);
   _a = new_A();
   ( (void(*)(_class_A *)) _a->vt[0](_class_A *) this);
   ( (void(*)(_class_A *)) _a->vt[1](_class_A *) this);
   ( (void(*)(_class_A *)) _a->vt[2](_class_A *) this);
   ( (void(*)(_class_A *)) _a->vt[3](_class_A *) this);
   ( (void(*)(_class_A *)) _a->vt[4](_class_A *) this);
   _b = new_B();
   ( (void(*)(_class_B *)) _b->vt[5](_class_B *) this);
   ( (void(*)(_class_B *)) _b->vt[0](_class_B *) this);
   ( (void(*)(_class_B *)) _b->vt[1](_class_B *) this);
   ( (void(*)(_class_B *)) _b->vt[2](_class_B *) this);
   ( (void(*)(_class_B *)) _b->vt[3](_class_B *) this);
   ( (void(*)(_class_B *)) _b->vt[4](_class_B *) this);
   _c = new_C();
   ( (void(*)(_class_C *)) _c->vt[5](_class_C *) this);
   ( (void(*)(_class_C *)) _c->vt[0](_class_C *) this);
   ( (void(*)(_class_C *)) _c->vt[5](_class_C *) this);
   ( (void(*)(_class_C *)) _c->vt[1](_class_C *) this);
   ( (void(*)(_class_C *)) _c->vt[2](_class_C *) this);
   ( (void(*)(_class_C *)) _c->vt[3](_class_C *) this);
   ( (void(*)(_class_C *)) _c->vt[4](_class_C *) this);
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
