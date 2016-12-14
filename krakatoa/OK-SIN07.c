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
      int _A_n;
      } _class_A;

_class_A *new_A(void);

void _A_put( _class_A *this,int _pn ) {
   this->_n = _pn;
}

int _A_get( _class_A *this ) {
   return this->_n;
}

Func VTClass_A[] = {
   ( void (*)() ) _A_get,
   ( void (*)() ) _A_put
};

_class_A *new_A()
{
   _class_A *t;

   if ( (t = malloc(sizeof(_class_A))) != NULL )
      t->vt = VTClass_A;
   return t;
}

typedef
   struct _St_No {
      Func *vt;
      _No _No_next;
      _A _No_a;
      } _class_No;

_class_No *new_No(void);

void _No_setNext( _class_No *this,_No _p_next ) {
   this->_next = _p_next;
}

_No _No_getNext( _class_No *this ) {
   return this->_next;
}

void _No_set( _class_No *this,_A _pa ) {
   this->_a = _pa;
}

_A _No_get( _class_No *this ) {
   return this->_a;
}

Func VTClass_No[] = {
   ( void (*)() ) _No_get,
   ( void (*)() ) _No_getNext,
   ( void (*)() ) _No_set,
   ( void (*)() ) _No_setNext
};

_class_No *new_No()
{
   _class_No *t;

   if ( (t = malloc(sizeof(_class_No))) != NULL )
      t->vt = VTClass_No;
   return t;
}

typedef
   struct _St_B {
      Func *vt;
      int _B_k;
      _No _B_first;
      } _class_B;

_class_B *new_B(void);

int _B_get( _class_B *this ) {
   printf("%d   ",this->_k);
   return ( (int(*)(_class_A *)) vt[1]();
}

void _B_init( _class_B *this,int _pk ) {
   this->_k = _pk;
}

void _B_buildList( _class_B *this ) {
   int _i;
   _class_No* _w;
   _class_No* _newNo;
   _class_A* _a;
   _i = ( (int(*)(_class_B *)) this->vt[1](this);
   this->_first = new_No();
   _w = this->_first;
   ( (void(*)(_class_No *, _No)) _w->vt[0](_w,   NULL);
   _a = new_A();
   ( (void(*)(_class_A *, int)) _a->vt[0](_a,_i);
   ( (void(*)(_class_No *, _A)) _w->vt[2](_w,_a);
   while (true) {
      i = i - 1;
      if (i <= 0) {
         break;
      }
      newNo = new No();
      newNo.setNext(this.first);
      a = new A();
      a.put(i);
      newNo.set(a);
      this.first = newNo;
   }
}

void _B_list( _class_B *this ) {
   _class_No* _w;
   _class_A* _a;
   _w = this->_first;
   while (_w !=    NULL) {
      a = w.get();
      write (a.get());
      w = w.getNext();
   }
}

Func VTClass_B[] = {
   ( void (*)() ) _B_buildList,
   ( void (*)() ) _B_get,
   ( void (*)() ) _B_init,
   ( void (*)() ) _B_list,
   ( void (*)() ) _A_put
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
   _class_B* _b;
   _b = new_B();
   ( (void(*)(_class_B *, int)) _b->vt[0](_b,10);
   ( (void(*)(_class_B *)) _b->vt[4](_b);
   ( (void(*)(_class_B *)) _b->vt[5](_b);
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
