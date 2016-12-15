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

int _A_get( _class_A *this ) {
   return this->_A_n;
}

void _A_set( _class_A *this,int _pn ) {
   this->_A_n = _pn;
}

Func VTClass_A[] = {
   ( void (*)() ) _A_get,
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

void _B_m( _class_B *this ) {
   int _i;
   {
      char __s[512];
      gets(__s);
      sscanf(__s,"%d",&_i);
   }
   {
      char __s[512];
      gets(__s);
      sscanf(__s,"%d",&this->_B_k);
   }
   ( (void(*)(_class_A *, int)) (_class_A *) this->vt[1])((_class_A *) this,0);
   printf("%d", ( (int(*)(_class_A *)) (_class_A *) this->vt[0])((_class_A *) this));
   printf("%d", ( (int(*)(_class_B *)) this->vt[0])(this));
   printf("%d", this->_B_k);
   printf("%d", _i);
}

void _B_print( _class_B *this ) {
   printf("%d", this->_B_k);
}

Func VTClass_B[] = {
   ( void (*)() ) _A_get,
   ( void (*)() ) _B_m,
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
   struct _St_Program {
      Func *vt;
      } _class_Program;

_class_Program *new_Program(void);

void _Program_run( _class_Program *this ) {
   _class_B *_b;
   _b = new_B();
   ( (void(*)(_class_B *, int)) _b->vt[1])(_b,1);
   ( (void(*)(_class_B *)) _b->vt[2])(_b);
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
