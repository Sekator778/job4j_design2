select st.name Family_Name, un.name UN
from students as st
         join universities as un on st.university_id = un.id
where st.budget = true;

select distinct un.id, un.name UN
from students as st
         join universities as un on st.university_id = un.id
where st.course >= 4
  and un.id = 1;

select un.name University_Name, st.name Name_Student, st.course Course_Student
from universities un
         join students as st on st.university_id = un.id
where st.name like 'D%';
