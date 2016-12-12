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
      boolean _A_b;
      char * _A_s;
      } _class_A;

_class_A *new_A(void);

void _A_m( _class_A *this ) {
   this->_n = (0);
   this->_b = false;
   this->_s = ("");
}

boolean _A_m_returns_boolean( _class_A *this ) {
   return this->_b;
}

void _A_m_integer( _class_A *this,int _n ) {
   this->_n = _n;
}

void _A_m_integer_boolean_String( _class_A *this,int _n, boolean _b, char * _s ) {
   this->_n = _n;
   this->_b = _b;
   this->_s = _s;
   printf("%s   ",this->_s);
}

boolean _A_m_integer_returns_boolean( _class_A *this,int _n ) {
   if (this->_n > _n) {
      return false;
   }

   else
      return this->_b;

}

boolean _A_m_integer_boolean_String_return( _class_A *this,int _n, boolean _b, char * _s ) {
   this->_s = _s;
   if (_b) {
      return ((_n + _n      ) > (0));
   }

   else
      return (this->_b && _b);

}

Func VTClass_A[] = {
   ( void (*)() ) _A_m,
   ( void (*)() ) _A_m_integer,
   ( void (*)() ) _A_m_integer_boolean_String,
   ( void (*)() ) _A_m_integer_boolean_String_return,
   ( void (*)() ) _A_m_integer_returns_boolean,
   ( void (*)() ) _A_m_returns_boolean
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

Func VTClass_B[] = {
   ( void (*)() ) _A_m,
   ( void (*)() ) _A_m_integer,
   ( void (*)() ) _A_m_integer_boolean_String,
   ( void (*)() ) _A_m_integer_boolean_String_return,
   ( void (*)() ) _A_m_integer_returns_boolean,
   ( void (*)() ) _A_m_returns_boolean
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
      char * _C_name;
      boolean _C_letter;
      int _C_n;
      int _C_time;
      } _class_C;

_class_C *new_C(void);

void _C_method( _class_C *this ) {
   this->_name = ("");
   this->_letter = false;
   this->_n = (0);
   this->_time = (0);
}

boolean _C_method_returns_boolean( _class_C *this ) {
   return this->_letter;
}

void _C_method_integer( _class_C *this,int _n ) {
   printf("%s %d %d   ",this->_name, _n, this->_time);
   if (this->_letter) {
      printf("%s      ","true");
   }

   else
      printf("%s      ","false");

}

void _C_method_integer_boolean_String( _class_C *this,int _n, boolean _b, char * _name ) {
   this->_name = _name;
   printf("%d   ",_n);
   if (_b) {
      printf("%d      ",0);
   }

   else
      printf("%d      ",1);

}

boolean _C_method_integer_returns_boolean( _class_C *this,int _n ) {
   return (this->_n > _n);
}

boolean _C_method_integer_boolean_String_r( _class_C *this,int _n, boolean _b, char * _name ) {
   printf("%s   ",_name);
   this->_name = _name;
   return ((this->_n > _n   ) && !(_b && this->_letter   ) && (this->_time > (0)   ));
}

Func VTClass_C[] = {
   ( void (*)() ) _C_method,
   ( void (*)() ) _C_method_integer,
   ( void (*)() ) _C_method_integer_boolean_String,
   ( void (*)() ) _C_method_integer_boolean_String_r,
   ( void (*)() ) _C_method_integer_returns_boolean,
   ( void (*)() ) _C_method_returns_boolean
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
   _B _b;
   _C _c;
   _b = new_B();
   ( (void(*)(_class_B *)) _b->vt[0](_class_B *) this);
   _c = new_C();
   ( (void(*)(_class_C *)) _c->vt[0](_class_C *) this);
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
