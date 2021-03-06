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
      boolean _A_b;
      char * _A_s;
      } _class_A;

_class_A *new_A(void);

void _A_m( _class_A *this ) {
   this->_A_n = (0);
   this->_A_b = false;
   strcpy(this->_A_s,"");
}

boolean _A_m_returns_boolean( _class_A *this ) {
   return this->_A_b;
}

void _A_m_integer( _class_A *this,int _n ) {
   this->_A_n = _n;
}

void _A_m_integer_boolean_String( _class_A *this,int _n, boolean _b, char * _s ) {
   this->_A_n = _n;
   this->_A_b = _b;
   strcpy(this->_A_s,_s);
   puts(this->_A_s);
}

boolean _A_m_integer_returns_boolean( _class_A *this,int _n ) {
   if (this->_A_n > _n) {
      return false;
   }

   else
      return this->_A_b;

}

boolean _A_m_integer_boolean_String_return( _class_A *this,int _n, boolean _b, char * _s ) {
   strcpy(this->_A_s,_s);
   if (_b) {
      return ((_n + _n) > (0));
   }

   else
      return (this->_A_b && _b);

}

Func VTClass_A[] = {
   ( void (*)() ) _A_m,
   ( void (*)() ) _A_m_returns_boolean,
   ( void (*)() ) _A_m_integer,
   ( void (*)() ) _A_m_integer_boolean_String,
   ( void (*)() ) _A_m_integer_returns_boolean,
   ( void (*)() ) _A_m_integer_boolean_String_return
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
      int _A_n;
      boolean _A_b;
      char * _A_s;
      } _class_B;

_class_B *new_B(void);

Func VTClass_B[] = {
   ( void (*)() ) _A_m,
   ( void (*)() ) _A_m_returns_boolean,
   ( void (*)() ) _A_m_integer,
   ( void (*)() ) _A_m_integer_boolean_String,
   ( void (*)() ) _A_m_integer_returns_boolean,
   ( void (*)() ) _A_m_integer_boolean_String_return
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
   strcpy(this->_C_name,"");
   this->_C_letter = false;
   this->_C_n = (0);
   this->_C_time = (0);
}

boolean _C_method_returns_boolean( _class_C *this ) {
   return this->_C_letter;
}

void _C_method_integer( _class_C *this,int _n ) {
   puts(this->_C_name);
   printf("%d ", _n);
   printf("%d ", this->_C_time);
   if (this->_C_letter) {
      puts("true");
   }

   else
      puts("false");

}

void _C_method_integer_boolean_String( _class_C *this,int _n, boolean _b, char * _name ) {
   strcpy(this->_C_name,_name);
   printf("%d ", _n);
   if (_b) {
      printf("%d ", 0);
   }

   else
      printf("%d ", 1);

}

boolean _C_method_integer_returns_boolean( _class_C *this,int _n ) {
   return (this->_C_n > _n);
}

boolean _C_method_integer_boolean_String_r( _class_C *this,int _n, boolean _b, char * _name ) {
   puts(_name);
   strcpy(this->_C_name,_name);
   return ((this->_C_n > _n) && !(_b && this->_C_letter) && (this->_C_time > (0)));
}

Func VTClass_C[] = {
   ( void (*)() ) _C_method,
   ( void (*)() ) _C_method_returns_boolean,
   ( void (*)() ) _C_method_integer,
   ( void (*)() ) _C_method_integer_boolean_String,
   ( void (*)() ) _C_method_integer_returns_boolean,
   ( void (*)() ) _C_method_integer_boolean_String_r
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
   _class_B *_b;
   _class_C *_c;
   _b = new_B();
   ( (void(*)(_class_B *)) _b->vt[0])(_b);
   _c = new_C();
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
