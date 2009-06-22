class AddCtcV2Terms extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        // Have to break up the inserts so as not to exceed the java max method length
        m0()
        m1()
        m2()
        m3()
        m4()
        m5()
        m6()
        m7()
        m8()
        m9()
        m10()
        m11()
        m12()
        m13()
        m14()
        m15()
        m16()
        m17()
        m18()
        m19()
        m20()
        m21()
        m22()
        m23()
        m24()
        m25()
        m26()
        m27()
        m28()
        m29()
        m30()
        m31()
        m32()
        m33()
        m34()
        m35()
        m36()
        m37()
        m38()
        m39()
        m40()
        m41()
        m42()
        m43()
        m44()
        m45()
        m46()
        m47()
        m48()
        m49()
        m50()
        m51()
        m52()
        m53()
        m54()
        m55()
        m56()
        m57()
        m58()
        m59()
        m60()
        m61()
        m62()
        m63()
        m64()
        m65()
        m66()
        m67()
        m68()
        m69()
        m70()
        m71()
        m72()
        m73()
        m74()
        m75()
        m76()
        m77()
        m78()
        m79()
        m80()
        m81()
        m82()
        m83()
        m84()
        m85()
        m86()
        m87()
        m88()
        m89()
        m90()
        m91()
        m92()
        m93()
        m94()
        m95()
        m96()
        m97()
        m98()
        m99()
        m100()
        m101()
        m102()
        m103()
        m104()
        m105()
        m106()
        m107()
        m108()
        m109()
        m110()
        m111()
        m112()
        m113()
        m114()
        m115()
        m116()
        m117()
        m118()
        m119()
        m120()
        m121()
        m122()
        m123()
        m124()
        m125()
        m126()
    }

    void m0() {
        insert('disease_categories', [ id: 1, name: "AIDS-related Malignancy and Condition" ], primaryKey: false)
    }

    void m1() {
        insert('disease_categories', [ id: 2, parent_id: 1, name: "AIDS-related Human Papillomavirus" ], primaryKey: false)
        insert('disease_terms', [ id: 1, term: "AIDS-related anal cancer", ctep_term: "AIDS-related anal cancer", category_id: 2, medra_code: 10065860], primaryKey: false)
        insert('disease_terms', [ id: 2, term: "AIDS-related cervical cancer", ctep_term: "AIDS-related cervical cancer", category_id: 2, medra_code: 10008229], primaryKey: false)
        insert('disease_terms', [ id: 3, term: "AIDS-related HPV infections", ctep_term: "AIDS-related HPV infections", category_id: 2, medra_code: 10063001], primaryKey: false)
        insert('disease_terms', [ id: 4, term: "AIDS-related HPV-related cancer", ctep_term: "AIDS-related HPV-related cancer, NOS", category_id: 2, medra_code: 90600068], primaryKey: false)
    }

    void m2() {
        insert('disease_categories', [ id: 3, parent_id: 1, name: "AIDS-related Kaposi Sarcoma" ], primaryKey: false)
        insert('disease_terms', [ id: 5, term: "AIDS-related Kaposi sarcoma", ctep_term: "AIDS-related Kaposi sarcoma", category_id: 3, medra_code: 10023290], primaryKey: false)
    }

    void m3() {
        insert('disease_categories', [ id: 4, parent_id: 1, name: "AIDS-related Lymphoma" ], primaryKey: false)
        insert('disease_terms', [ id: 6, term: "AIDS-related Hodgkin lymphoma", ctep_term: "AIDS-related Hodgkin lymphoma", category_id: 4, medra_code: 90600076], primaryKey: false)
        insert('disease_terms', [ id: 7, term: "AIDS-related NHL", ctep_term: "AIDS-related Non-Hodgkin lymphoma", category_id: 4, medra_code: 90600308], primaryKey: false)
        insert('disease_terms', [ id: 8, term: "AIDS-related primary CNS lymphoma", ctep_term: "AIDS-related primary CNS lymphoma", category_id: 4, medra_code: 90600080], primaryKey: false)
    }

    void m4() {
        insert('disease_categories', [ id: 5, parent_id: 1, name: "AIDS-related Malignancy and Condition, Miscellaneous" ], primaryKey: false)
        insert('disease_terms', [ id: 9, term: "AIDS-related complications", ctep_term: "AIDS-related complications", category_id: 5, medra_code: 10049026], primaryKey: false)
        insert('disease_terms', [ id: 10, term: "Diffuse infiltra. lymph. sydrome", ctep_term: "Diffuse infiltrative lymphocytosis syndrome", category_id: 5, medra_code: 10025280], primaryKey: false)
        insert('disease_terms', [ id: 11, term: "HIV test positive", ctep_term: "HIV test positive", category_id: 5, medra_code: 10020188], primaryKey: false)
    }

    void m5() {
        insert('disease_categories', [ id: 6, name: "Bone Neoplasm" ], primaryKey: false)
    }

    void m6() {
        insert('disease_categories', [ id: 7, parent_id: 6, name: "Bone Neoplasm, Miscellaneous" ], primaryKey: false)
        insert('disease_terms', [ id: 12, term: "Bone cancer, NOS", ctep_term: "Bone cancer, NOS", category_id: 7, medra_code: 10053650], primaryKey: false)
    }

    void m7() {
        insert('disease_categories', [ id: 8, parent_id: 6, name: "Chondrosarcoma" ], primaryKey: false)
        insert('disease_terms', [ id: 13, term: "Chondrosarcoma", ctep_term: "Chondrosarcoma", category_id: 8, medra_code: 10008737], primaryKey: false)
    }

    void m8() {
        insert('disease_categories', [ id: 9, parent_id: 6, name: "Ewing Sarcoma" ], primaryKey: false)
        insert('disease_terms', [ id: 14, term: "Ewing sarcoma/Peripheral PNET", ctep_term: "Ewing sarcoma/Peripheral primitive neuroectodermal tumor", category_id: 9, medra_code: 10015563], primaryKey: false)
    }

    void m9() {
        insert('disease_categories', [ id: 10, parent_id: 6, name: "Osteosarcoma" ], primaryKey: false)
        insert('disease_terms', [ id: 15, term: "Osteosarcoma", ctep_term: "Osteosarcoma", category_id: 10, medra_code: 10031291], primaryKey: false)
    }

    void m10() {
        insert('disease_categories', [ id: 11, name: "Breast Neoplasm" ], primaryKey: false)
    }

    void m11() {
        insert('disease_categories', [ id: 12, parent_id: 11, name: "Breast Cancer - In situ" ], primaryKey: false)
        insert('disease_terms', [ id: 16, term: "Ductal carcinoma in situ", ctep_term: "Ductal carcinoma in situ", category_id: 12, medra_code: 10013806], primaryKey: false)
        insert('disease_terms', [ id: 17, term: "Lobular carcinoma in situ", ctep_term: "Lobular carcinoma in situ", category_id: 12, medra_code: 10024744], primaryKey: false)
    }

    void m12() {
        insert('disease_categories', [ id: 13, parent_id: 11, name: "Breast Cancer - Invasive" ], primaryKey: false)
        insert('disease_terms', [ id: 18, term: "Cystosarcoma phylloides - breast", ctep_term: "Cystosarcoma phylloides of the breast", category_id: 13, medra_code: 10053130], primaryKey: false)
        insert('disease_terms', [ id: 19, term: "Inflammatory breast carcinoma", ctep_term: "Inflammatory breast carcinoma", category_id: 13, medra_code: 10021980], primaryKey: false)
        insert('disease_terms', [ id: 20, term: "Invasive breast carcinoma", ctep_term: "Invasive breast carcinoma", category_id: 13, medra_code: 10006190], primaryKey: false)
    }

    void m13() {
        insert('disease_categories', [ id: 14, parent_id: 11, name: "Breast Neoplasm, Miscellaneous" ], primaryKey: false)
        insert('disease_terms', [ id: 21, term: "Breast cancer, NOS", ctep_term: "Breast cancer, NOS", category_id: 14, medra_code: 10006285], primaryKey: false)
    }

    void m14() {
        insert('disease_categories', [ id: 15, name: "CNS Neoplasm (Primary Tumor)" ], primaryKey: false)
    }

    void m15() {
        insert('disease_categories', [ id: 16, parent_id: 15, name: "Astrocytoma, High Grade" ], primaryKey: false)
        insert('disease_terms', [ id: 22, term: "Anaplastic astrocytoma", ctep_term: "Anaplastic astrocytoma", category_id: 16, medra_code: 10002224], primaryKey: false)
        insert('disease_terms', [ id: 23, term: "Diffuse brainstem glioma", ctep_term: "Diffuse brainstem glioma", category_id: 16, medra_code: 10006143], primaryKey: false)
        insert('disease_terms', [ id: 24, term: "Glioblastoma multiforme", ctep_term: "Glioblastoma multiforme", category_id: 16, medra_code: 10018337], primaryKey: false)
        insert('disease_terms', [ id: 25, term: "High-grade astrocytoma, NOS", ctep_term: "High-grade astrocytoma, NOS", category_id: 16, medra_code: 10008093], primaryKey: false)
    }

    void m16() {
        insert('disease_categories', [ id: 17, parent_id: 15, name: "Astrocytoma, Low Grade" ], primaryKey: false)
        insert('disease_terms', [ id: 26, term: "Fibrillary astrocytoma", ctep_term: "Fibrillary astrocytoma", category_id: 17, medra_code: 10065889], primaryKey: false)
        insert('disease_terms', [ id: 27, term: "Low-grade astrocytoma, NOS", ctep_term: "Low-grade astrocytoma, NOS", category_id: 17, medra_code: 10003571], primaryKey: false)
        insert('disease_terms', [ id: 28, term: "Pilocytic astrocytoma", ctep_term: "Pilocytic astrocytoma", category_id: 17, medra_code: 90600112], primaryKey: false)
    }

    void m17() {
        insert('disease_categories', [ id: 18, parent_id: 15, name: "Choroid Plexus Tumors" ], primaryKey: false)
        insert('disease_terms', [ id: 29, term: "Choroid plexus carcinoma", ctep_term: "Choroid plexus carcinoma", category_id: 18, medra_code: 90600120], primaryKey: false)
    }

    void m18() {
        insert('disease_categories', [ id: 19, parent_id: 15, name: "CNS Neoplasm (Primary Tumor), Miscellaneous" ], primaryKey: false)
        insert('disease_terms', [ id: 30, term: "CNS primary tumor, NOS", ctep_term: "CNS primary tumor, NOS", category_id: 19, medra_code: 10007959], primaryKey: false)
        insert('disease_terms', [ id: 31, term: "Pineal parenchymal tumor", ctep_term: "Pineal parenchymal tumor", category_id: 19, medra_code: 10035054], primaryKey: false)
    }

    void m19() {
        insert('disease_categories', [ id: 20, parent_id: 15, name: "Embryonal Tumors of the CNS" ], primaryKey: false)
        insert('disease_terms', [ id: 32, term: "Atypical teratoid/rhabdoid tumor", ctep_term: "Atypical teratoid/rhabdoid tumor", category_id: 20, medra_code: 10065870], primaryKey: false)
        insert('disease_terms', [ id: 33, term: "Medulloblastoma", ctep_term: "Medulloblastoma", category_id: 20, medra_code: 10027107], primaryKey: false)
        insert('disease_terms', [ id: 34, term: "Supratent. primitive neuro. tumor", ctep_term: "Supratentorial primitive neuroectodermal tumor", category_id: 20, medra_code: 10056672], primaryKey: false)
    }

    void m20() {
        insert('disease_categories', [ id: 21, parent_id: 15, name: "Ependymal Tumors" ], primaryKey: false)
        insert('disease_terms', [ id: 35, term: "Ependymoma, NOS", ctep_term: "Ependymoma, NOS", category_id: 21, medra_code: 10014967], primaryKey: false)
    }

    void m21() {
        insert('disease_categories', [ id: 22, parent_id: 15, name: "Germ Cell Tumors of the CNS" ], primaryKey: false)
        insert('disease_terms', [ id: 36, term: "Germinoma of CNS", ctep_term: "Germinoma of CNS", category_id: 22, medra_code: 10065852], primaryKey: false)
        insert('disease_terms', [ id: 37, term: "Nongerminomatous GCT - CNS", ctep_term: "Nongerminomatous germ cell tumor of the CNS", category_id: 22, medra_code: 10065853], primaryKey: false)
    }

    void m22() {
        insert('disease_categories', [ id: 23, parent_id: 15, name: "Meningeal Tumors" ], primaryKey: false)
        insert('disease_terms', [ id: 38, term: "Meningioma, NOS", ctep_term: "Meningioma, NOS", category_id: 23, medra_code: 10027189], primaryKey: false)
    }

    void m23() {
        insert('disease_categories', [ id: 24, parent_id: 15, name: "Oligodendroglial Tumors" ], primaryKey: false)
        insert('disease_terms', [ id: 39, term: "Anaplastic oligodendroglioma", ctep_term: "Anaplastic oligodendroglioma", category_id: 24, medra_code: 10026659], primaryKey: false)
        insert('disease_terms', [ id: 40, term: "Oligodendroglioma, NOS", ctep_term: "Oligodendroglioma, NOS", category_id: 24, medra_code: 10030286], primaryKey: false)
    }

    void m24() {
        insert('disease_categories', [ id: 25, parent_id: 15, name: "Tumors of the Sellar Region" ], primaryKey: false)
        insert('disease_terms', [ id: 41, term: "Craniopharyngioma", ctep_term: "Craniopharyngioma", category_id: 25, medra_code: 10011318], primaryKey: false)
        insert('disease_terms', [ id: 42, term: "Pituitary gland cancer, NOS", ctep_term: "Pituitary gland cancer, NOS", category_id: 25, medra_code: 10035106], primaryKey: false)
    }

    void m25() {
        insert('disease_categories', [ id: 26, name: "CNS-excluded Nervous System Neoplasm and Disorder" ], primaryKey: false)
    }

    void m26() {
        insert('disease_categories', [ id: 27, parent_id: 26, name: "CNS-excluded Nervous System Neoplasm, Miscellaneous" ], primaryKey: false)
        insert('disease_terms', [ id: 43, term: "CNS-excluded nervous sys. cancer", ctep_term: "CNS-excluded nervous system cancer, NOS", category_id: 27, medra_code: 10029208], primaryKey: false)
    }

    void m27() {
        insert('disease_categories', [ id: 28, parent_id: 26, name: "Neuroblastoma" ], primaryKey: false)
        insert('disease_terms', [ id: 44, term: "Neuroblastoma", ctep_term: "Neuroblastoma", category_id: 28, medra_code: 10029261], primaryKey: false)
    }

    void m28() {
        insert('disease_categories', [ id: 29, parent_id: 26, name: "Neurofibromatosis" ], primaryKey: false)
        insert('disease_terms', [ id: 45, term: "Neurofibromatosis", ctep_term: "Neurofibromatosis", category_id: 29, medra_code: 10029268], primaryKey: false)
    }

    void m29() {
        insert('disease_categories', [ id: 30, name: "Endocrine Neoplasm" ], primaryKey: false)
    }

    void m30() {
        insert('disease_categories', [ id: 31, parent_id: 30, name: "Adrenal Cancer" ], primaryKey: false)
        insert('disease_terms', [ id: 46, term: "Adrenocortical carcinoma", ctep_term: "Adrenocortical carcinoma, NOS", category_id: 31, medra_code: 10001327], primaryKey: false)
    }

    void m31() {
        insert('disease_categories', [ id: 32, parent_id: 30, name: "Endocrine Neoplasm, Miscellaneous" ], primaryKey: false)
        insert('disease_terms', [ id: 47, term: "Endocrine cancer, NOS", ctep_term: "Endocrine cancer, NOS", category_id: 32, medra_code: 10014709], primaryKey: false)
    }

    void m32() {
        insert('disease_categories', [ id: 33, parent_id: 30, name: "Neuroendocrine Cancer" ], primaryKey: false)
        insert('disease_terms', [ id: 48, term: "Carcinoid tumor", ctep_term: "Carcinoid tumor", category_id: 33, medra_code: 10007276], primaryKey: false)
        insert('disease_terms', [ id: 49, term: "Islet cell tumors - pancreas", ctep_term: "Islet cell tumors of the pancreas", category_id: 33, medra_code: 10033630], primaryKey: false)
        insert('disease_terms', [ id: 50, term: "Neuroendocrine cancer, NOS", ctep_term: "Neuroendocrine cancer, NOS", category_id: 33, medra_code: 10057270], primaryKey: false)
        insert('disease_terms', [ id: 51, term: "Pheochromocytoma (adrenal)", ctep_term: "Pheochromocytoma (adrenal)", category_id: 33, medra_code: 10034876], primaryKey: false)
        insert('disease_terms', [ id: 52, term: "Small cell car. (extrapulmonary)", ctep_term: "Small cell carcinoma (extrapulmonary)", category_id: 33, medra_code: 10041056], primaryKey: false)
    }

    void m33() {
        insert('disease_categories', [ id: 34, parent_id: 30, name: "Parathyroid Cancer" ], primaryKey: false)
        insert('disease_terms', [ id: 53, term: "Parathyroid cancer, NOS", ctep_term: "Parathyroid cancer, NOS", category_id: 34, medra_code: 10033965], primaryKey: false)
    }

    void m34() {
        insert('disease_categories', [ id: 35, parent_id: 30, name: "Thyroid Cancer" ], primaryKey: false)
        insert('disease_terms', [ id: 54, term: "Anaplastic thyroid carcinoma", ctep_term: "Anaplastic thyroid carcinoma", category_id: 35, medra_code: 10002240], primaryKey: false)
        insert('disease_terms', [ id: 55, term: "Follicular thyroid carcinoma", ctep_term: "Follicular thyroid carcinoma", category_id: 35, medra_code: 10016935], primaryKey: false)
        insert('disease_terms', [ id: 56, term: "Hurthle cell neoplasm (thyroid)", ctep_term: "Hurthle cell neoplasm (thyroid)", category_id: 35, medra_code: 10065861], primaryKey: false)
        insert('disease_terms', [ id: 57, term: "Medullary thyroid carcinoma", ctep_term: "Medullary thyroid carcinoma", category_id: 35, medra_code: 10027105], primaryKey: false)
        insert('disease_terms', [ id: 58, term: "Papillary thyroid carcinoma", ctep_term: "Papillary thyroid carcinoma", category_id: 35, medra_code: 10033701], primaryKey: false)
        insert('disease_terms', [ id: 59, term: "Thyroid cancer, NOS", ctep_term: "Thyroid cancer, NOS", category_id: 35, medra_code: 10043738], primaryKey: false)
    }

    void m35() {
        insert('disease_categories', [ id: 36, name: "Eye Neoplasm" ], primaryKey: false)
    }

    void m36() {
        insert('disease_categories', [ id: 37, parent_id: 36, name: "Malignant Eye Neoplasm, Miscellaneous" ], primaryKey: false)
        insert('disease_terms', [ id: 60, term: "Malignant eye cancer, NOS", ctep_term: "Malignant eye cancer, NOS", category_id: 37, medra_code: 10025549], primaryKey: false)
    }

    void m37() {
        insert('disease_categories', [ id: 38, parent_id: 36, name: "Retinoblastoma" ], primaryKey: false)
        insert('disease_terms', [ id: 61, term: "Retinoblastoma", ctep_term: "Retinoblastoma", category_id: 38, medra_code: 10038918], primaryKey: false)
    }

    void m38() {
        insert('disease_categories', [ id: 39, name: "Gastrointestinal Neoplasm" ], primaryKey: false)
    }

    void m39() {
        insert('disease_categories', [ id: 40, parent_id: 39, name: "Anal Cancer" ], primaryKey: false)
        insert('disease_terms', [ id: 62, term: "Anal cancer", ctep_term: "Anal cancer, NOS", category_id: 40, medra_code: 10002126], primaryKey: false)
        insert('disease_terms', [ id: 63, term: "Squamous cell carcinoma - anus", ctep_term: "Squamous cell carcinoma of the anus", category_id: 40, medra_code: 10041840], primaryKey: false)
    }

    void m40() {
        insert('disease_categories', [ id: 41, parent_id: 39, name: "Colorectal Cancer" ], primaryKey: false)
        insert('disease_terms', [ id: 64, term: "Adenocarcinoma - colon", ctep_term: "Adenocarcinoma of the colon", category_id: 41, medra_code: 10009951], primaryKey: false)
        insert('disease_terms', [ id: 65, term: "Adenocarcinoma - rectum", ctep_term: "Adenocarcinoma of the rectum", category_id: 41, medra_code: 10038045], primaryKey: false)
        insert('disease_terms', [ id: 66, term: "Colorectal cancer, NOS", ctep_term: "Colorectal cancer, NOS", category_id: 41, medra_code: 10010029], primaryKey: false)
    }

    void m41() {
        insert('disease_categories', [ id: 42, parent_id: 39, name: "Gastroesophageal Cancer" ], primaryKey: false)
        insert('disease_terms', [ id: 67, term: "Adenocarcinoma - esophagus", ctep_term: "Adenocarcinoma of the esophagus", category_id: 42, medra_code: 10030139], primaryKey: false)
        insert('disease_terms', [ id: 68, term: "Adenocarcinoma - GEJ", ctep_term: "Adenocarcinoma of the gastroesophageal junction", category_id: 42, medra_code: 90002022], primaryKey: false)
        insert('disease_terms', [ id: 69, term: "Adenocarcinoma - stomach", ctep_term: "Adenocarcinoma of the stomach", category_id: 42, medra_code: 10001150], primaryKey: false)
        insert('disease_terms', [ id: 70, term: "Esophageal cancer, NOS", ctep_term: "Esophageal cancer, NOS", category_id: 42, medra_code: 10030157], primaryKey: false)
        insert('disease_terms', [ id: 71, term: "Gastric cancer, NOS", ctep_term: "Gastric cancer, NOS", category_id: 42, medra_code: 10017760], primaryKey: false)
        insert('disease_terms', [ id: 72, term: "Squamous cell car. - esophagus", ctep_term: "Squamous cell carcinoma of the esophagus", category_id: 42, medra_code: 10030186], primaryKey: false)
    }

    void m42() {
        insert('disease_categories', [ id: 43, parent_id: 39, name: "Gastrointestinal Neoplasm, Miscellaneous" ], primaryKey: false)
        insert('disease_terms', [ id: 73, term: "Gastrointestinal cancer, NOS", ctep_term: "Gastrointestinal cancer, NOS", category_id: 43, medra_code: 10017986], primaryKey: false)
    }

    void m43() {
        insert('disease_categories', [ id: 44, parent_id: 39, name: "Liver and Hepatobiliary Cancer" ], primaryKey: false)
        insert('disease_terms', [ id: 74, term: "Cholangiocar.- intra/extrahepatic", ctep_term: "Cholangiocarcinoma, intrahepatic and extrahepatic bile ducts (adenocarcinoma)", category_id: 44, medra_code: 10004669], primaryKey: false)
        insert('disease_terms', [ id: 75, term: "Gall bladder carcinoma (adeno)", ctep_term: "Gall bladder carcinoma (adenocarcinoma)", category_id: 44, medra_code: 10017618], primaryKey: false)
        insert('disease_terms', [ id: 76, term: "Hepatoblastoma", ctep_term: "Hepatoblastoma", category_id: 44, medra_code: 10019822], primaryKey: false)
        insert('disease_terms', [ id: 77, term: "Hepatocellular carcinoma", ctep_term: "Hepatocellular carcinoma", category_id: 44, medra_code: 10049010], primaryKey: false)
        insert('disease_terms', [ id: 78, term: "Liver/hepatobiliary cancer", ctep_term: "Liver and hepatobiliary cancer, NOS", category_id: 44, medra_code: 10019810], primaryKey: false)
    }

    void m44() {
        insert('disease_categories', [ id: 45, parent_id: 39, name: "Pancreatic Cancer (excluding Islets)" ], primaryKey: false)
        insert('disease_terms', [ id: 79, term: "Adenocarcinoma - pancreas", ctep_term: "Adenocarcinoma of the pancreas", category_id: 45, medra_code: 10052747], primaryKey: false)
        insert('disease_terms', [ id: 80, term: "Cystadenocarcinoma - pancreas", ctep_term: "Cystadenocarcinoma of the pancreas", category_id: 45, medra_code: 10065908], primaryKey: false)
        insert('disease_terms', [ id: 81, term: "Pancreatic cancer (not Islets)", ctep_term: "Pancreatic cancer (excluding Islets), NOS", category_id: 45, medra_code: 10033612], primaryKey: false)
    }

    void m45() {
        insert('disease_categories', [ id: 46, parent_id: 39, name: "Small Intestine Cancer" ], primaryKey: false)
        insert('disease_terms', [ id: 82, term: "Adenocarcinoma - small intest.", ctep_term: "Adenocarcinoma of the small intestine", category_id: 46, medra_code: 10041115], primaryKey: false)
        insert('disease_terms', [ id: 83, term: "Small intestine cancer, NOS", ctep_term: "Small intestine cancer, NOS", category_id: 46, medra_code: 10054184], primaryKey: false)
    }

    void m46() {
        insert('disease_categories', [ id: 47, name: "Germ Cell Neoplasm" ], primaryKey: false)
    }

    void m47() {
        insert('disease_categories', [ id: 48, parent_id: 47, name: "Extragonadal Germ Cell Cancer (excluding Gestational Trophoblastic Disease and Germ Cell Tumors of the CNS)" ], primaryKey: false)
        insert('disease_terms', [ id: 84, term: "Extragonadal GCT, NOS", ctep_term: "Extragonadal germ cell tumors, NOS", category_id: 48, medra_code: 10015790], primaryKey: false)
    }

    void m48() {
        insert('disease_categories', [ id: 49, parent_id: 47, name: "Germ Cell Neoplasm, Miscellaneous" ], primaryKey: false)
        insert('disease_terms', [ id: 85, term: "Germ cell cancer, NOS", ctep_term: "Germ cell cancer, NOS", category_id: 49, medra_code: 10018204], primaryKey: false)
    }

    void m49() {
        insert('disease_categories', [ id: 50, parent_id: 47, name: "Ovarian Germ Cell Cancer" ], primaryKey: false)
        insert('disease_terms', [ id: 86, term: "Ovarian dysgerminoma", ctep_term: "Ovarian dysgerminoma", category_id: 50, medra_code: 10052759], primaryKey: false)
        insert('disease_terms', [ id: 87, term: "Ovarian nondysgerm. GCT, NOS", ctep_term: "Ovarian nondysgerminomatous germ cell tumor, NOS", category_id: 50, medra_code: 10052311], primaryKey: false)
    }

    void m50() {
        insert('disease_categories', [ id: 51, parent_id: 47, name: "Testicular Germ Cell Cancer" ], primaryKey: false)
        insert('disease_terms', [ id: 88, term: "Testicular nonseminomat. GCT, NOS", ctep_term: "Testicular nonseminomatous germ cell tumor, NOS", category_id: 51, medra_code: 10043321], primaryKey: false)
        insert('disease_terms', [ id: 89, term: "Testicular seminoma", ctep_term: "Testicular seminoma", category_id: 51, medra_code: 10043353], primaryKey: false)
    }

    void m51() {
        insert('disease_categories', [ id: 52, name: "Head and Neck Neoplasm" ], primaryKey: false)
    }

    void m52() {
        insert('disease_categories', [ id: 53, parent_id: 52, name: "Head and Neck Neoplasm, Miscellaneous" ], primaryKey: false)
        insert('disease_terms', [ id: 90, term: "Head & neck cancer, NOS", ctep_term: "Head and neck cancer, NOS", category_id: 53, medra_code: 10025960], primaryKey: false)
    }

    void m53() {
        insert('disease_categories', [ id: 54, parent_id: 52, name: "Head and Neck Precancerous Condition" ], primaryKey: false)
        insert('disease_terms', [ id: 91, term: "H & N precancerous condition", ctep_term: "Head and neck precancerous condition", category_id: 54, medra_code: 90600168], primaryKey: false)
    }

    void m54() {
        insert('disease_categories', [ id: 55, parent_id: 52, name: "Head and Neck Squamous Cell Carcinoma" ], primaryKey: false)
        insert('disease_terms', [ id: 92, term: "H & N squamous cell car., NOS", ctep_term: "Head and neck squamous cell carcinoma, NOS", category_id: 55, medra_code: 10060121], primaryKey: false)
        insert('disease_terms', [ id: 93, term: "Laryngeal squamous cell carcinoma", ctep_term: "Laryngeal squamous cell carcinoma", category_id: 55, medra_code: 10023843], primaryKey: false)
        insert('disease_terms', [ id: 94, term: "Lip/oral cavity squam. cell car.", ctep_term: "Lip and oral cavity squamous cell carcinoma", category_id: 55, medra_code: 10024534], primaryKey: false)
        insert('disease_terms', [ id: 95, term: "Pharyngeal squam. cell carcinoma", ctep_term: "Pharyngeal (including Hypopharyngeal and Oropharyngeal) squamous cell carcinoma", category_id: 55, medra_code: 10034819], primaryKey: false)
    }

    void m55() {
        insert('disease_categories', [ id: 56, parent_id: 52, name: "Nasopharyngeal Cancer" ], primaryKey: false)
        insert('disease_terms', [ id: 96, term: "Nasopharyngeal carcinoma", ctep_term: "Nasopharyngeal carcinoma", category_id: 56, medra_code: 10028786], primaryKey: false)
    }

    void m56() {
        insert('disease_categories', [ id: 57, parent_id: 52, name: "Salivary Gland Cancer" ], primaryKey: false)
        insert('disease_terms', [ id: 97, term: "Salivary gland cancer", ctep_term: "Salivary gland cancer", category_id: 57, medra_code: 10039397], primaryKey: false)
    }

    void m57() {
        insert('disease_categories', [ id: 58, name: "Hematopoietic Neoplasm (excluding Leukemia, Lymphoma and Myeloma)" ], primaryKey: false)
    }

    void m58() {
        insert('disease_categories', [ id: 59, parent_id: 58, name: "Hematopoietic Neoplasm, Miscellaneous" ], primaryKey: false)
        insert('disease_terms', [ id: 98, term: "Hematopoietic malignancy, NOS", ctep_term: "Hematopoietic malignancy, NOS", category_id: 59, medra_code: 10018864], primaryKey: false)
        insert('disease_terms', [ id: 99, term: "Langerhans cell histiocytosis", ctep_term: "Langerhans cell histiocytosis", category_id: 59, medra_code: 10025581], primaryKey: false)
    }

    void m59() {
        insert('disease_categories', [ id: 60, parent_id: 58, name: "Mastocytosis" ], primaryKey: false)
        insert('disease_terms', [ id: 100, term: "Systemic mastocytosis, NOS", ctep_term: "Systemic mastocytosis, NOS", category_id: 60, medra_code: 10025638], primaryKey: false)
    }

    void m60() {
        insert('disease_categories', [ id: 61, name: "Hematopoietic Neoplasm/Leukemia" ], primaryKey: false)
    }

    void m61() {
        insert('disease_categories', [ id: 62, parent_id: 61, name: "Acute Lymphoblastic Leukemia" ], primaryKey: false)
        insert('disease_terms', [ id: 101, term: "Acute lymphoblastic leukemia", ctep_term: "Acute lymphoblastic leukemia, NOS", category_id: 62, medra_code: 10000846], primaryKey: false)
        insert('disease_terms', [ id: 102, term: "B-precursor ALL", ctep_term: "Precursor B-lymphoblastic leukemia (B-precursor ALL)", category_id: 62, medra_code: 10003890], primaryKey: false)
        insert('disease_terms', [ id: 103, term: "T-cell ALL", ctep_term: "Precursor T-lymphoblastic leukemia (T-cell ALL)", category_id: 62, medra_code: 10054569], primaryKey: false)
    }

    void m62() {
        insert('disease_categories', [ id: 63, parent_id: 61, name: "Acute Myeloid Leukemia" ], primaryKey: false)
        insert('disease_terms', [ id: 104, term: "Acute myeloid leukemia", ctep_term: "Acute myeloid leukemia, NOS", category_id: 63, medra_code: 10000884], primaryKey: false)
        insert('disease_terms', [ id: 105, term: "Acute promyelocytic leukemia", ctep_term: "Acute promyelocytic leukemia", category_id: 63, medra_code: 10001019], primaryKey: false)
        insert('disease_terms', [ id: 106, term: "Treatment-related AML", ctep_term: "Treatment-related acute myeloid leukemia", category_id: 63, medra_code: 90600316], primaryKey: false)
    }

    void m63() {
        insert('disease_categories', [ id: 64, parent_id: 61, name: "Chronic Lymphoid Leukemia" ], primaryKey: false)
        insert('disease_terms', [ id: 107, term: "Adult T-cell leukemia/lymphoma", ctep_term: "Adult T-cell leukemia/lymphoma", category_id: 64, medra_code: 10001415], primaryKey: false)
        insert('disease_terms', [ id: 108, term: "Chronic lymphocytic leukemia, NOS", ctep_term: "Chronic lymphocytic leukemia, NOS", category_id: 64, medra_code: 10008960], primaryKey: false)
        insert('disease_terms', [ id: 109, term: "Hairy cell leukemia", ctep_term: "Hairy cell leukemia", category_id: 64, medra_code: 10019053], primaryKey: false)
        insert('disease_terms', [ id: 110, term: "Prolymphocytic leukemia (B or T)", ctep_term: "Prolymphocytic leukemia (B or T-cell)", category_id: 64, medra_code: 10036888], primaryKey: false)
        insert('disease_terms', [ id: 111, term: "T-cell large gran. lymph. leuk.", ctep_term: "T-cell large granular lymphocytic leukemia", category_id: 64, medra_code: 10065862], primaryKey: false)
    }

    void m64() {
        insert('disease_categories', [ id: 65, parent_id: 61, name: "Chronic Myeloproliferative Disease" ], primaryKey: false)
        insert('disease_terms', [ id: 112, term: "CEL/Hypereosinophilic syndrome", ctep_term: "Chronic eosinophilic leukemia/hypereosinophilic syndrome", category_id: 65, medra_code: 10065872], primaryKey: false)
        insert('disease_terms', [ id: 113, term: "Chronic idiopathic myelofibrosis", ctep_term: "Chronic idiopathic myelofibrosis", category_id: 65, medra_code: 10028537], primaryKey: false)
        insert('disease_terms', [ id: 114, term: "CML - Philadelphia chromosome", ctep_term: "Chronic myelogenous leukemia, Philadelphia chromosome positive", category_id: 65, medra_code: 10009013], primaryKey: false)
        insert('disease_terms', [ id: 115, term: "Essential thrombocythemia", ctep_term: "Essential thrombocythemia", category_id: 65, medra_code: 10015493], primaryKey: false)
        insert('disease_terms', [ id: 116, term: "Polycythemia vera", ctep_term: "Polycythemia vera", category_id: 65, medra_code: 10036061], primaryKey: false)
    }

    void m65() {
        insert('disease_categories', [ id: 66, parent_id: 61, name: "Leukemia, Miscellaneous" ], primaryKey: false)
        insert('disease_terms', [ id: 117, term: "Leukemia, NOS", ctep_term: "Leukemia, NOS", category_id: 66, medra_code: 10024312], primaryKey: false)
    }

    void m66() {
        insert('disease_categories', [ id: 67, parent_id: 61, name: "Myelodysplastic Syndrome" ], primaryKey: false)
        insert('disease_terms', [ id: 118, term: "Myelodysplastic syndrome, NOS", ctep_term: "Myelodysplastic syndrome, NOS", category_id: 67, medra_code: 10028534], primaryKey: false)
    }

    void m67() {
        insert('disease_categories', [ id: 68, parent_id: 61, name: "Myelodysplastic/Myeloproliferative Disease" ], primaryKey: false)
        insert('disease_terms', [ id: 119, term: "Chronic myelomonocytic leukemia", ctep_term: "Chronic myelomonocytic leukemia", category_id: 68, medra_code: 10009018], primaryKey: false)
        insert('disease_terms', [ id: 120, term: "Juvenile myelomonocytic leukemia", ctep_term: "Juvenile myelomonocytic leukemia", category_id: 68, medra_code: 10023249], primaryKey: false)
    }

    void m68() {
        insert('disease_categories', [ id: 69, name: "Hematopoietic Neoplasm/Lymphoma" ], primaryKey: false)
    }

    void m69() {
        insert('disease_categories', [ id: 70, parent_id: 69, name: "B-Cell Proliferations of Uncertain Malignant Potential" ], primaryKey: false)
        insert('disease_terms', [ id: 121, term: "Lymphomatoid granulomatosis", ctep_term: "Lymphomatoid granulomatosis", category_id: 70, medra_code: 10025325], primaryKey: false)
    }

    void m70() {
        insert('disease_categories', [ id: 71, parent_id: 69, name: "Hodgkin Lymphoma" ], primaryKey: false)
        insert('disease_terms', [ id: 122, term: "Hodgkin lymphoma nodular LP, NOS", ctep_term: "Hodgkin lymphoma nodular lymphocyte predominant type, NOS", category_id: 71, medra_code: 10020231], primaryKey: false)
        insert('disease_terms', [ id: 123, term: "Hodgkin lymphoma, NOS", ctep_term: "Hodgkin lymphoma, NOS", category_id: 71, medra_code: 10020255], primaryKey: false)
        insert('disease_terms', [ id: 124, term: "Hodgkin lymphoma - like PTLD", ctep_term: "Hodgkin lymphoma-like post-transplant lymphoproliferative disorder", category_id: 71, medra_code: 90600328], primaryKey: false)
    }

    void m71() {
        insert('disease_categories', [ id: 72, parent_id: 69, name: "Lymphoma, Miscellaneous" ], primaryKey: false)
        insert('disease_terms', [ id: 125, term: "Lymphoma, NOS", ctep_term: "Lymphoma, NOS", category_id: 72, medra_code: 10025316], primaryKey: false)
    }

    void m72() {
        insert('disease_categories', [ id: 73, parent_id: 69, name: "Non-Hodgkin Lymphoma" ], primaryKey: false)
        insert('disease_terms', [ id: 126, term: "ALCL, cutaneous", ctep_term: "Anaplastic large-cell lymphoma, primary cutaneous type ", category_id: 73, medra_code: 10065863], primaryKey: false)
        insert('disease_terms', [ id: 127, term: "ALCL, systemic", ctep_term: "Anaplastic large-cell lymphoma, primary systemic type", category_id: 73, medra_code: 10065864], primaryKey: false)
        insert('disease_terms', [ id: 128, term: "Burkitt lymphoma/leukemia", ctep_term: "Burkitt lymphoma/leukemia", category_id: 73, medra_code: 10006597], primaryKey: false)
        insert('disease_terms', [ id: 129, term: "CTCL/ Mycosis fungoides", ctep_term: "Cutaneous T-cell lymphoma/Mycosis fungoides", category_id: 73, medra_code: 10028500], primaryKey: false)
        insert('disease_terms', [ id: 130, term: "CTCL / Sezary syndrome", ctep_term: "Cutaneous T-cell lymphoma/Sezary syndrome", category_id: 73, medra_code: 10011677], primaryKey: false)
        insert('disease_terms', [ id: 131, term: "Diffuse large B-cell lymphoma", ctep_term: "Diffuse large B-cell lymphoma", category_id: 73, medra_code: 10012820], primaryKey: false)
        insert('disease_terms', [ id: 132, term: "MALT-lymphoma", ctep_term: "Extranodal marginal zone B-cell lymphoma of mucosa-associated lymphoid tissue (MALT-lymphoma)", category_id: 73, medra_code: 10015822], primaryKey: false)
        insert('disease_terms', [ id: 133, term: "Extranodal NK/T lymphoma-nasal", ctep_term: "Extranodal NK/T-cell lymphoma, nasal type", category_id: 73, medra_code: 10065855], primaryKey: false)
        insert('disease_terms', [ id: 134, term: "Follicular lymphoma", ctep_term: "Follicular lymphoma", category_id: 73, medra_code: 10016896], primaryKey: false)
        insert('disease_terms', [ id: 135, term: "Lymphoplasmacytic lymphoma", ctep_term: "Lymphoplasmacytic lymphoma (Waldenstrom macroglobulinemia)", category_id: 73, medra_code: 10047803], primaryKey: false)
        insert('disease_terms', [ id: 136, term: "Mantle cell lymphoma", ctep_term: "Mantle cell lymphoma", category_id: 73, medra_code: 10026799], primaryKey: false)
        insert('disease_terms', [ id: 137, term: "Mediastinal large B-cell lymphoma", ctep_term: "Mediastinal (thymic) large B-cell lymphoma", category_id: 73, medra_code: 10036712], primaryKey: false)
        insert('disease_terms', [ id: 138, term: "Nodal marginal zone B-cell lymph.", ctep_term: "Nodal marginal zone B-cell lymphoma", category_id: 73, medra_code: 10029462], primaryKey: false)
        insert('disease_terms', [ id: 139, term: "NHL, aggressive, NOS", ctep_term: "Non-Hodgkin lymphoma, aggressive, NOS", category_id: 73, medra_code: 10063908], primaryKey: false)
        insert('disease_terms', [ id: 140, term: "NHL, indolent, NOS", ctep_term: "Non-Hodgkin lymphoma, indolent, NOS", category_id: 73, medra_code: 10065856], primaryKey: false)
        insert('disease_terms', [ id: 141, term: "NHL, NOS", ctep_term: "Non-Hodgkin lymphoma, NOS", category_id: 73, medra_code: 10029593], primaryKey: false)
        insert('disease_terms', [ id: 142, term: "Peripheral T-cell lymphoma, NOS", ctep_term: "Peripheral T-cell lymphoma, NOS", category_id: 73, medra_code: 10034624], primaryKey: false)
        insert('disease_terms', [ id: 143, term: "PTLD (Monoclonal)", ctep_term: "Post-transplant lymphoproliferative disorder (Monoclonal), NOS", category_id: 73, medra_code: 90600336], primaryKey: false)
        insert('disease_terms', [ id: 144, term: "PTLD (Polyclonal)", ctep_term: "Post-transplant lymphoproliferative disorder (Polyclonal), NOS", category_id: 73, medra_code: 90600340], primaryKey: false)
        insert('disease_terms', [ id: 145, term: "Precur. B-lymphoblastic lymphoma", ctep_term: "Precursor B-lymphoblastic lymphoma", category_id: 73, medra_code: 10036525], primaryKey: false)
        insert('disease_terms', [ id: 146, term: "Precur. T-lymphoblastic lymphoma", ctep_term: "Precursor T-lymphoblastic lymphoma", category_id: 73, medra_code: 10036545], primaryKey: false)
        insert('disease_terms', [ id: 147, term: "Primary CNS lymphoma", ctep_term: "Primary CNS lymphoma", category_id: 73, medra_code: 10007953], primaryKey: false)
        insert('disease_terms', [ id: 148, term: "Primary effusion lymphoma", ctep_term: "Primary effusion lymphoma", category_id: 73, medra_code: 10065857], primaryKey: false)
        insert('disease_terms', [ id: 149, term: "Small lymphocytic lymphoma, NOS", ctep_term: "Small lymphocytic lymphoma, NOS", category_id: 73, medra_code: 10003910], primaryKey: false)
    }

    void m73() {
        insert('disease_categories', [ id: 74, name: "Hematopoietic Neoplasm/Myeloma" ], primaryKey: false)
    }

    void m74() {
        insert('disease_categories', [ id: 75, parent_id: 74, name: "Myeloma" ], primaryKey: false)
        insert('disease_terms', [ id: 150, term: "MGUS", ctep_term: "Monoclonal gammopathy of undetermined significance (MGUS)", category_id: 75, medra_code: 10020631], primaryKey: false)
        insert('disease_terms', [ id: 151, term: "Myeloma, NOS", ctep_term: "Myeloma, NOS", category_id: 75, medra_code: 10028566], primaryKey: false)
        insert('disease_terms', [ id: 152, term: "Solitary plasmacytoma", ctep_term: "Solitary plasmacytoma", category_id: 75, medra_code: 10035484], primaryKey: false)
    }

    void m75() {
        insert('disease_categories', [ id: 76, name: "Immune Disorder (excluding AIDS and malignancy)" ], primaryKey: false)
    }

    void m76() {
        insert('disease_categories', [ id: 77, parent_id: 76, name: "Immune Disorder (excluding AIDS and malignancy), Miscellaneous" ], primaryKey: false)
        insert('disease_terms', [ id: 153, term: "Amyloidosis", ctep_term: "Amyloidosis", category_id: 77, medra_code: 10002024], primaryKey: false)
        insert('disease_terms', [ id: 154, term: "GVHD, acute", ctep_term: "Graft versus host disease, acute", category_id: 77, medra_code: 10000802], primaryKey: false)
        insert('disease_terms', [ id: 155, term: "GVHD, chronic", ctep_term: "Graft versus host disease, chronic", category_id: 77, medra_code: 10008907], primaryKey: false)
        insert('disease_terms', [ id: 156, term: "Immune disorder, NOS", ctep_term: "Immune disorder, NOS", category_id: 77, medra_code: 10021425], primaryKey: false)
    }

    void m77() {
        insert('disease_categories', [ id: 78, parent_id: 76, name: "Immunodeficiency and Immunosuppression Disorder" ], primaryKey: false)
        insert('disease_terms', [ id: 157, term: "Immunosuppression disorders", ctep_term: "Immunosuppression disorders", category_id: 78, medra_code: 10021510], primaryKey: false)
        insert('disease_terms', [ id: 158, term: "Lymphoadenopathy w/ polyclonal", ctep_term: "Lymphoadenopathy with polyclonal hypergammaglobulinemia", category_id: 78, medra_code: 90002028], primaryKey: false)
        insert('disease_terms', [ id: 159, term: "Lymphocytic interst. pneumonitis", ctep_term: "Lymphocytic interstitial pneumonitis", category_id: 78, medra_code: 10035755], primaryKey: false)
    }

    void m78() {
        insert('disease_categories', [ id: 79, name: "Kidney Neoplasm" ], primaryKey: false)
    }

    void m79() {
        insert('disease_categories', [ id: 80, parent_id: 79, name: "Clear Cell Sarcoma of the Kidney" ], primaryKey: false)
        insert('disease_terms', [ id: 160, term: "Clear cell sarcoma - kidney", ctep_term: "Clear cell sarcoma of the kidney", category_id: 80, medra_code: 10009253], primaryKey: false)
    }

    void m80() {
        insert('disease_categories', [ id: 81, parent_id: 79, name: "Kidney Neoplasm, Miscellaneous" ], primaryKey: false)
        insert('disease_terms', [ id: 161, term: "Kidney cancer, NOS", ctep_term: "Kidney cancer, NOS", category_id: 81, medra_code: 10038485], primaryKey: false)
    }

    void m81() {
        insert('disease_categories', [ id: 82, parent_id: 79, name: "Renal Cell Carcinoma" ], primaryKey: false)
        insert('disease_terms', [ id: 162, term: "Collecting duct renal cancer", ctep_term: "Collecting duct renal cancer", category_id: 82, medra_code: 90600176], primaryKey: false)
        insert('disease_terms', [ id: 163, term: "Papillary renal cell carcinoma", ctep_term: "Papillary renal cell carcinoma", category_id: 82, medra_code: 10033702], primaryKey: false)
        insert('disease_terms', [ id: 164, term: "RCC w/ sarcomatoid features", ctep_term: "Renal cell carcinoma with sarcomatoid features", category_id: 82, medra_code: 90600180], primaryKey: false)
        insert('disease_terms', [ id: 165, term: "RCC, clear cell adenocarcinoma", ctep_term: "Renal cell carcinoma, clear cell adenocarcinoma", category_id: 82, medra_code: 10009251], primaryKey: false)
        insert('disease_terms', [ id: 166, term: "Renal cell carcinoma, NOS", ctep_term: "Renal cell carcinoma, NOS", category_id: 82, medra_code: 10038415], primaryKey: false)
    }

    void m82() {
        insert('disease_categories', [ id: 83, parent_id: 79, name: "Rhabdoid Tumor of the Kidney" ], primaryKey: false)
        insert('disease_terms', [ id: 167, term: "Rhabdoid tumor of the kidney", ctep_term: "Rhabdoid tumor of the kidney", category_id: 83, medra_code: 10039019], primaryKey: false)
    }

    void m83() {
        insert('disease_categories', [ id: 84, parent_id: 79, name: "Wilms Tumor" ], primaryKey: false)
        insert('disease_terms', [ id: 168, term: "Wilms tumor (Nephroblastoma)", ctep_term: "Wilms tumor (Nephroblastoma)", category_id: 84, medra_code: 10029145], primaryKey: false)
    }

    void m84() {
        insert('disease_categories', [ id: 85, name: "Lung, Mediastinal and Pleural Neoplasm" ], primaryKey: false)
    }

    void m85() {
        insert('disease_categories', [ id: 86, parent_id: 85, name: "Lung, Mediastinal and Pleural Neoplasm, Miscellaneous" ], primaryKey: false)
        insert('disease_terms', [ id: 169, term: "Lung cancer, NOS", ctep_term: "Lung cancer, NOS", category_id: 86, medra_code: 10025065], primaryKey: false)
    }

    void m86() {
        insert('disease_categories', [ id: 87, parent_id: 85, name: "Mediastinal Cancer" ], primaryKey: false)
        insert('disease_terms', [ id: 170, term: "Thymoma", ctep_term: "Thymoma", category_id: 87, medra_code: 10043673], primaryKey: false)
    }

    void m87() {
        insert('disease_categories', [ id: 88, parent_id: 85, name: "Mesothelioma" ], primaryKey: false)
        insert('disease_terms', [ id: 171, term: "Mesothelioma", ctep_term: "Mesothelioma", category_id: 88, medra_code: 10027410], primaryKey: false)
    }

    void m88() {
        insert('disease_categories', [ id: 89, parent_id: 85, name: "Non-Small Cell Lung Cancer" ], primaryKey: false)
        insert('disease_terms', [ id: 172, term: "Bronchioloalveolar carcinoma", ctep_term: "Bronchioloalveolar carcinoma", category_id: 89, medra_code: 10058354], primaryKey: false)
        insert('disease_terms', [ id: 173, term: "Lung adenocarcinoma", ctep_term: "Lung adenocarcinoma", category_id: 89, medra_code: 10025032], primaryKey: false)
        insert('disease_terms', [ id: 174, term: "Lung adenocar. w/ bronch. feat.", ctep_term: "Lung adenocarcinoma with bronchioloalveolar features", category_id: 89, medra_code: 90600324], primaryKey: false)
        insert('disease_terms', [ id: 175, term: "Non-small cell lung cancer, NOS", ctep_term: "Non-small cell lung cancer, NOS", category_id: 89, medra_code: 10029514], primaryKey: false)
        insert('disease_terms', [ id: 176, term: "Squamous cell lung carcinoma", ctep_term: "Squamous cell lung carcinoma", category_id: 89, medra_code: 10025126], primaryKey: false)
    }

    void m89() {
        insert('disease_categories', [ id: 90, parent_id: 85, name: "Small Cell Lung Cancer" ], primaryKey: false)
        insert('disease_terms', [ id: 177, term: "Small cell lung cancer", ctep_term: "Small cell lung cancer", category_id: 90, medra_code: 10041071], primaryKey: false)
    }

    void m90() {
        insert('disease_categories', [ id: 91, name: "Miscellaneous Neoplasm" ], primaryKey: false)
    }

    void m91() {
        insert('disease_categories', [ id: 92, parent_id: 91, name: "Carcinoma, Miscellaneous" ], primaryKey: false)
        insert('disease_terms', [ id: 178, term: "Adenocarcinoma, NOS", ctep_term: "Adenocarcinoma, NOS", category_id: 92, medra_code: 10001166], primaryKey: false)
        insert('disease_terms', [ id: 179, term: "Carcinoma, NOS", ctep_term: "Carcinoma, NOS", category_id: 92, medra_code: 10007423], primaryKey: false)
    }

    void m92() {
        insert('disease_categories', [ id: 93, parent_id: 91, name: "Metastases, Distant (excluding specified site of origin)" ], primaryKey: false)
        insert('disease_terms', [ id: 180, term: "Leptomeningeal metastases, NOS", ctep_term: "Leptomeningeal metastases, NOS", category_id: 93, medra_code: 10024233], primaryKey: false)
        insert('disease_terms', [ id: 181, term: "Malignant ascites, NOS", ctep_term: "Malignant ascites, NOS", category_id: 93, medra_code: 10025538], primaryKey: false)
        insert('disease_terms', [ id: 182, term: "Metastases to bone, NOS", ctep_term: "Metastases to bone, NOS", category_id: 93, medra_code: 10027452], primaryKey: false)
        insert('disease_terms', [ id: 183, term: "Metast. to brain parenchyma, NOS", ctep_term: "Metastases to brain parenchyma, NOS", category_id: 93, medra_code: 10059282], primaryKey: false)
        insert('disease_terms', [ id: 184, term: "Metastases to liver, NOS", ctep_term: "Metastases to liver, NOS", category_id: 93, medra_code: 10027457], primaryKey: false)
        insert('disease_terms', [ id: 185, term: "Metastases to lung, NOS", ctep_term: "Metastases to lung, NOS", category_id: 93, medra_code: 10027458], primaryKey: false)
        insert('disease_terms', [ id: 186, term: "Metastases to peritoneum, NOS", ctep_term: "Metastases to peritoneum, NOS", category_id: 93, medra_code: 10051676], primaryKey: false)
        insert('disease_terms', [ id: 187, term: "Metastases to skin, NOS", ctep_term: "Metastases to skin, NOS", category_id: 93, medra_code: 10027465], primaryKey: false)
        insert('disease_terms', [ id: 188, term: "Pleural effusion, NOS", ctep_term: "Pleural effusion, NOS", category_id: 93, medra_code: 10026673], primaryKey: false)
    }

    void m93() {
        insert('disease_categories', [ id: 94, parent_id: 91, name: "Miscellaneous Neoplasm " ], primaryKey: false)
        insert('disease_terms', [ id: 189, term: "Miscellaneous neoplasm, NOS", ctep_term: "Miscellaneous neoplasm, NOS", category_id: 94, medra_code: 10025691], primaryKey: false)
    }

    void m94() {
        insert('disease_categories', [ id: 95, parent_id: 91, name: "Solid Tumor, Miscellaneous" ], primaryKey: false)
        insert('disease_terms', [ id: 190, term: "Solid tumor, NOS", ctep_term: "Solid tumor, NOS", category_id: 95, medra_code: 10029000], primaryKey: false)
    }

    void m95() {
        insert('disease_categories', [ id: 96, parent_id: 91, name: "Vascular Tumor, Miscellaneous" ], primaryKey: false)
        insert('disease_terms', [ id: 191, term: "Vascular tumor, NOS", ctep_term: "Vascular tumor, NOS", category_id: 96, medra_code: 10055031], primaryKey: false)
    }

    void m96() {
        insert('disease_categories', [ id: 97, name: "Non-neoplastic Disorder" ], primaryKey: false)
    }

    void m97() {
        insert('disease_categories', [ id: 98, parent_id: 97, name: "Non-neoplastic Disorder, Miscellaneous" ], primaryKey: false)
        insert('disease_terms', [ id: 192, term: "Non-neoplastic condition, NOS", ctep_term: "Non-neoplastic disorder, NOS", category_id: 98, medra_code: 90600296], primaryKey: false)
    }

    void m98() {
        insert('disease_categories', [ id: 99, name: "Reproductive System Neoplasm, Female" ], primaryKey: false)
    }

    void m99() {
        insert('disease_categories', [ id: 100, parent_id: 99, name: "Cervical Cancer" ], primaryKey: false)
        insert('disease_terms', [ id: 193, term: "Adenocarcinoma - cervix", ctep_term: "Adenocarcinoma of the cervix", category_id: 100, medra_code: 10008224], primaryKey: false)
        insert('disease_terms', [ id: 194, term: "Cervical cancer, NOS", ctep_term: "Cervical cancer, NOS", category_id: 100, medra_code: 10008238], primaryKey: false)
        insert('disease_terms', [ id: 195, term: "Squamous cervical cancer", ctep_term: "Squamous cervical cancer", category_id: 100, medra_code: 10041848], primaryKey: false)
    }

    void m100() {
        insert('disease_categories', [ id: 101, parent_id: 99, name: "Cervical Intraepithelial Neoplasia (CIN)" ], primaryKey: false)
        insert('disease_terms', [ id: 196, term: "Cervical intraepith. neoplasia", ctep_term: "Cervical intraepithelial neoplasia (CIN)", category_id: 101, medra_code: 10059383], primaryKey: false)
    }

    void m101() {
        insert('disease_categories', [ id: 102, parent_id: 99, name: "Female Reproductive System Neoplasm, Miscellaneous" ], primaryKey: false)
        insert('disease_terms', [ id: 197, term: "Female reprod. system cancer, NOS", ctep_term: "Female reproductive system cancer, NOS", category_id: 102, medra_code: 10016411], primaryKey: false)
    }

    void m102() {
        insert('disease_categories', [ id: 103, parent_id: 99, name: "Gestational Trophoblastic Disease" ], primaryKey: false)
        insert('disease_terms', [ id: 198, term: "Gestational trophoblastic disease", ctep_term: "Gestational trophoblastic disease", category_id: 103, medra_code: 10018212], primaryKey: false)
    }

    void m103() {
        insert('disease_categories', [ id: 104, parent_id: 99, name: "Ovarian Cancer (excluding Ovarian Germ Cell Cancer)" ], primaryKey: false)
        insert('disease_terms', [ id: 199, term: "Fallopian tube carcinoma", ctep_term: "Fallopian tube carcinoma", category_id: 104, medra_code: 10016180], primaryKey: false)
        insert('disease_terms', [ id: 200, term: "Ovarian cancer, NOS", ctep_term: "Ovarian cancer, NOS", category_id: 104, medra_code: 10033272], primaryKey: false)
        insert('disease_terms', [ id: 201, term: "Ovarian epithelial cancer", ctep_term: "Ovarian epithelial cancer", category_id: 104, medra_code: 10033159], primaryKey: false)
        insert('disease_terms', [ id: 202, term: "Ovarian stromal cancer", ctep_term: "Ovarian stromal cancer", category_id: 104, medra_code: 10065858], primaryKey: false)
        insert('disease_terms', [ id: 203, term: "Ovarian tum. of low malig. poten.", ctep_term: "Ovarian tumors of low malignant potential", category_id: 104, medra_code: 10033268], primaryKey: false)
        insert('disease_terms', [ id: 204, term: "Primary peritoneal carcinoma", ctep_term: "Primary peritoneal carcinoma", category_id: 104, medra_code: 10026669], primaryKey: false)
    }

    void m104() {
        insert('disease_categories', [ id: 105, parent_id: 99, name: "Uterine Cancer" ], primaryKey: false)
        insert('disease_terms', [ id: 205, term: "Carcinosarcoma of the uterus", ctep_term: "Carcinosarcoma of the uterus", category_id: 105, medra_code: 10007508], primaryKey: false)
        insert('disease_terms', [ id: 206, term: "Endometrial stromal sarcoma", ctep_term: "Endometrial stromal sarcoma", category_id: 105, medra_code: 10048397], primaryKey: false)
        insert('disease_terms', [ id: 207, term: "Endometrioid endomet. adenocar.", ctep_term: "Endometrioid endometrial adenocarcinoma", category_id: 105, medra_code: 10014735], primaryKey: false)
        insert('disease_terms', [ id: 208, term: "Leiomyosarcoma - uterus", ctep_term: "Leiomyosarcoma of the uterus", category_id: 105, medra_code: 10046799], primaryKey: false)
        insert('disease_terms', [ id: 209, term: "Serous endometrial adenocarcinoma", ctep_term: "Serous endometrial adenocarcinoma", category_id: 105, medra_code: 10033700], primaryKey: false)
        insert('disease_terms', [ id: 210, term: "Uterine cancer, NOS", ctep_term: "Uterine cancer, NOS", category_id: 105, medra_code: 10046804], primaryKey: false)
    }

    void m105() {
        insert('disease_categories', [ id: 106, parent_id: 99, name: "Vaginal Cancer" ], primaryKey: false)
        insert('disease_terms', [ id: 211, term: "Vaginal cancer, NOS", ctep_term: "Vaginal cancer, NOS", category_id: 106, medra_code: 10046888], primaryKey: false)
    }

    void m106() {
        insert('disease_categories', [ id: 107, parent_id: 99, name: "Vaginal Intraepithelial Neoplasia (VIN)" ], primaryKey: false)
        insert('disease_terms', [ id: 212, term: "Vaginal intraepithelial neoplasia", ctep_term: "Vaginal intraepithelial neoplasia (VIN)", category_id: 107, medra_code: 10046890], primaryKey: false)
    }

    void m107() {
        insert('disease_categories', [ id: 108, parent_id: 99, name: "Vulvar Cancer" ], primaryKey: false)
        insert('disease_terms', [ id: 213, term: "Vulvar cancer, NOS", ctep_term: "Vulvar cancer, NOS", category_id: 108, medra_code: 10047743], primaryKey: false)
    }

    void m108() {
        insert('disease_categories', [ id: 109, name: "Reproductive System Neoplasm, Male" ], primaryKey: false)
    }

    void m109() {
        insert('disease_categories', [ id: 110, parent_id: 109, name: "Male Reproductive System Neoplasm, Miscellaneous" ], primaryKey: false)
        insert('disease_terms', [ id: 214, term: "Male reprod. system cancer, NOS", ctep_term: "Male reproductive system cancer, NOS", category_id: 110, medra_code: 10025519], primaryKey: false)
    }

    void m110() {
        insert('disease_categories', [ id: 111, parent_id: 109, name: "Penile Cancer" ], primaryKey: false)
        insert('disease_terms', [ id: 215, term: "Penile adenocarcinoma", ctep_term: "Penile adenocarcinoma", category_id: 111, medra_code: 90600236], primaryKey: false)
        insert('disease_terms', [ id: 216, term: "Penile cancer, NOS", ctep_term: "Penile cancer, NOS", category_id: 111, medra_code: 10034330], primaryKey: false)
        insert('disease_terms', [ id: 217, term: "Penile squamous car.(epidermoid)", ctep_term: "Penile squamous carcinoma (epidermoid)", category_id: 111, medra_code: 10059631], primaryKey: false)
    }

    void m111() {
        insert('disease_categories', [ id: 112, parent_id: 109, name: "Prostate Cancer" ], primaryKey: false)
        insert('disease_terms', [ id: 218, term: "Prostate cancer, NOS", ctep_term: "Prostate cancer, NOS", category_id: 112, medra_code: 10036910], primaryKey: false)
    }

    void m112() {
        insert('disease_categories', [ id: 113, parent_id: 109, name: "Prostatic Intraepithelial Neoplasia (PIN)" ], primaryKey: false)
        insert('disease_terms', [ id: 219, term: "Prostate intraepithelial neopl.", ctep_term: "Prostate intraepithelial neoplasia (PIN)", category_id: 113, medra_code: 10036912], primaryKey: false)
    }

    void m113() {
        insert('disease_categories', [ id: 114, parent_id: 109, name: "Testicular Cancer (excluding Testicular Germ Cell Cancer)" ], primaryKey: false)
        insert('disease_terms', [ id: 220, term: "Testicular ca. (no germ/tropho.)", ctep_term: "Testicular cancer (excluding germ cell or trophoblastic cancer)", category_id: 114, medra_code: 10043342], primaryKey: false)
    }

    void m114() {
        insert('disease_categories', [ id: 115, name: "Skin Neoplasm" ], primaryKey: false)
    }

    void m115() {
        insert('disease_categories', [ id: 116, parent_id: 115, name: "Basal Cell Carcinoma" ], primaryKey: false)
        insert('disease_terms', [ id: 221, term: "Basal cell carcinoma", ctep_term: "Basal cell carcinoma", category_id: 116, medra_code: 10004146], primaryKey: false)
    }

    void m116() {
        insert('disease_categories', [ id: 117, parent_id: 115, name: "Melanoma" ], primaryKey: false)
        insert('disease_terms', [ id: 222, term: "Melanoma", ctep_term: "Melanoma", category_id: 117, medra_code: 10053571], primaryKey: false)
    }

    void m117() {
        insert('disease_categories', [ id: 118, parent_id: 115, name: "Merkel Cell Tumor" ], primaryKey: false)
        insert('disease_terms', [ id: 223, term: "Merkel cell tumor", ctep_term: "Merkel cell tumor", category_id: 118, medra_code: 10029266], primaryKey: false)
    }

    void m118() {
        insert('disease_categories', [ id: 119, parent_id: 115, name: "Skin Neoplasm, Miscellaneous" ], primaryKey: false)
        insert('disease_terms', [ id: 224, term: "Skin cancer, NOS", ctep_term: "Skin cancer, NOS", category_id: 119, medra_code: 10040811], primaryKey: false)
    }

    void m119() {
        insert('disease_categories', [ id: 120, parent_id: 115, name: "Squamous Cell Carcinoma of the Skin" ], primaryKey: false)
        insert('disease_terms', [ id: 225, term: "Squamous cell carcinoma - skin", ctep_term: "Squamous cell carcinoma of the skin", category_id: 120, medra_code: 10041834], primaryKey: false)
    }

    void m120() {
        insert('disease_categories', [ id: 121, name: "Soft Tissue Neoplasm" ], primaryKey: false)
    }

    void m121() {
        insert('disease_categories', [ id: 122, parent_id: 121, name: "Non-Rhabdomyosarcoma Soft Tissue Sarcoma" ], primaryKey: false)
        insert('disease_terms', [ id: 226, term: "Alveolar soft part sarcoma", ctep_term: "Alveolar soft part sarcoma", category_id: 122, medra_code: 10001886], primaryKey: false)
        insert('disease_terms', [ id: 227, term: "Clear cell sarcoma - not kidney", ctep_term: "Clear cell sarcoma/Malignant melanoma of soft parts (excluding Clear cell sarcoma of the kidney)", category_id: 122, medra_code: 10065865], primaryKey: false)
        insert('disease_terms', [ id: 228, term: "Dermatofibrosarcoma", ctep_term: "Dermatofibrosarcoma", category_id: 122, medra_code: 10057043], primaryKey: false)
        insert('disease_terms', [ id: 229, term: "Desmoplas. small round cell tumor", ctep_term: "Desmoplastic small round cell tumor", category_id: 122, medra_code: 10064587], primaryKey: false)
        insert('disease_terms', [ id: 230, term: "Fibrosarcoma - not infantile", ctep_term: "Fibrosarcoma (excluding infantile fibrosarcoma)", category_id: 122, medra_code: 10016637], primaryKey: false)
        insert('disease_terms', [ id: 231, term: "Gastrointestinal stromal tumor", ctep_term: "Gastrointestinal stromal tumor", category_id: 122, medra_code: 10051066], primaryKey: false)
        insert('disease_terms', [ id: 232, term: "Infantile fibrosarcoma", ctep_term: "Infantile fibrosarcoma (congenital fibrosarcoma)", category_id: 122, medra_code: 10065859], primaryKey: false)
        insert('disease_terms', [ id: 233, term: "Leiomyosarcoma - not uterine", ctep_term: "Leiomyosarcoma (excluding uterine leiomyosarcoma)", category_id: 122, medra_code: 10024193], primaryKey: false)
        insert('disease_terms', [ id: 234, term: "Liposarcoma", ctep_term: "Liposarcoma", category_id: 122, medra_code: 10024631], primaryKey: false)
        insert('disease_terms', [ id: 235, term: "Malignant fibrous histiocytoma", ctep_term: "Malignant fibrous histiocytoma", category_id: 122, medra_code: 10025556], primaryKey: false)
        insert('disease_terms', [ id: 236, term: "Malig. periph. nerve sheath tum.", ctep_term: "Malignant peripheral nerve sheath tumor", category_id: 122, medra_code: 10026667], primaryKey: false)
        insert('disease_terms', [ id: 237, term: "Non-Rhabdo. soft tissue sarcoma", ctep_term: "Non-Rhabdomyosarcoma soft tissue sarcoma, NOS", category_id: 122, medra_code: 10039494], primaryKey: false)
        insert('disease_terms', [ id: 238, term: "Plexiform neurofibroma", ctep_term: "Plexiform neurofibroma", category_id: 122, medra_code: 10065866], primaryKey: false)
        insert('disease_terms', [ id: 239, term: "Synovial sarcoma", ctep_term: "Synovial sarcoma", category_id: 122, medra_code: 10042866], primaryKey: false)
    }

    void m122() {
        insert('disease_categories', [ id: 123, parent_id: 121, name: "Rhabdomyosarcoma" ], primaryKey: false)
        insert('disease_terms', [ id: 240, term: "Alveolar rhabdomyosarcoma", ctep_term: "Alveolar rhabdomyosarcoma", category_id: 123, medra_code: 10065867], primaryKey: false)
        insert('disease_terms', [ id: 241, term: "Embryonal rhabdomyosarcoma", ctep_term: "Embryonal rhabdomyosarcoma", category_id: 123, medra_code: 10065868], primaryKey: false)
        insert('disease_terms', [ id: 242, term: "Rhabdomyosarcoma, NOS", ctep_term: "Rhabdomyosarcoma, NOS", category_id: 123, medra_code: 10039024], primaryKey: false)
    }

    void m123() {
        insert('disease_categories', [ id: 124, parent_id: 121, name: "Soft Tissue Neoplasm, Miscellaneous" ], primaryKey: false)
        insert('disease_terms', [ id: 243, term: "Soft tissue neoplasm, NOS", ctep_term: "Soft tissue neoplasm, NOS", category_id: 124, medra_code: 10061271], primaryKey: false)
    }

    void m124() {
        insert('disease_categories', [ id: 125, name: "Urothelial Tract Neoplasm" ], primaryKey: false)
    }

    void m125() {
        insert('disease_categories', [ id: 126, parent_id: 125, name: "Urothelial Tract/Bladder Cancer" ], primaryKey: false)
        insert('disease_terms', [ id: 244, term: "Transitional cell car. - uroth.", ctep_term: "Transitional cell carcinoma of the urothelial tract", category_id: 126, medra_code: 10044409], primaryKey: false)
    }

    void m126() {
        insert('disease_categories', [ id: 127, parent_id: 125, name: "Urothelial Tract/Bladder Neoplasm, Miscellaneous" ], primaryKey: false)
        insert('disease_terms', [ id: 245, term: "Urothelial/bladder cancer, NOS", ctep_term: "Urothelial tract/bladder cancer, NOS", category_id: 127, medra_code: 10018192], primaryKey: false)
    }

    void down() {
        execute("DELETE FROM disease_terms ")
        execute("DELETE FROM disease_categories ")
    }
}
