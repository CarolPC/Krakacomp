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
   struct _St_Point {
      Func *vt;
      int _Point_x;
      int _Point_y;
      } _class_Point;

_class_Point *new_Point(void);

void _Point_set( _class_Point *this,int _x, int _y ) {
   this->_Point_x = _x;
   this->_Point_y = _y;
}

int _Point_getX( _class_Point *this ) {
   return this->_Point_x;
}

int _Point_getY( _class_Point *this ) {
   return this->_Point_y;
}

Func VTClass_Point[] = {
   ( void (*)() ) _Point_getX,
   ( void (*)() ) _Point_getY,
   ( void (*)() ) _Point_set
};

_class_Point *new_Point()
{
   _class_Point *t;

   if ( (t = malloc(sizeof(_class_Point))) != NULL )
      t->vt = VTClass_Point;
   return t;
}

typedef
   struct _St_Program {
      Func *vt;
      _class_Point* _Program_p;
      } _class_Program;

_class_Program *new_Program(void);

void _Program_run( _class_Program *this ) {
   this->_Program_p = new_Point();
   ( (void(*)(_class_Point *, int, int)) this->_Program_p->vt[0])(this->_Program_p,0, 0);
}

_class_Point* _Program_getPoint( _class_Program *this ) {
   return this->_Program_p;
}

Func VTClass_Program[] = {
   ( void (*)() ) _Program_getPoint,
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
